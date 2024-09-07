package com.eduflix.eduflix.AdminPanel.Service;

import com.eduflix.eduflix.AdminPanel.Dto.CourseListResponseDto;
import com.eduflix.eduflix.AdminPanel.Dto.DashboardStatistic;
import com.eduflix.eduflix.AdminPanel.Dto.SubscriptionStatistics;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Repository.ClassesRepository;
import com.eduflix.eduflix.Repository.CourseRepository;
import com.eduflix.eduflix.Repository.StudentRepository;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;

@Service
@AllArgsConstructor
public class AdminDashboardService {

    private final CourseRepository courseRepository;
    private final ClassesRepository classRepository;
    private final StudentRepository studentRepository;
    private final UsersRepository usersRepository;

    public List<CourseListResponseDto> getAllCourseList() {
        List<Course> courses = courseRepository.findAll();
        return getCourseListResponseDtos(courses);
    }

    private int getStudentNumberOfCourse(Course course) {
        return classRepository.countStudents(course.getId());
    }

    private int getClassesNumber(Course course) {
        return classRepository.countClasses(course.getId());
    }

    public DashboardStatistic totalSigning() {
        DashboardStatistic dashboardStatistic = new DashboardStatistic();
        dashboardStatistic.setNumber(studentRepository.countStudents());
        dashboardStatistic.setName("Total Signing");
        return dashboardStatistic;
    }

    public DashboardStatistic monthlySigning() {
        DashboardStatistic dashboardStatistic = new DashboardStatistic();
        YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue());
        LocalDate begin = yearMonth.atDay(1);
        dashboardStatistic.setNumber(usersRepository.monthlyCountStudents(begin,LocalDate.now()));
        dashboardStatistic.setName("Monthly Signing");
        return dashboardStatistic;
    }


    public DashboardStatistic totalCourse() {
        DashboardStatistic dashboardStatistic = new DashboardStatistic();
        dashboardStatistic.setNumber(courseRepository.countCourse());
        dashboardStatistic.setName("Total Course");
        return dashboardStatistic;
    }

    public DashboardStatistic totalClass() {
        DashboardStatistic dashboardStatistic = new DashboardStatistic();
        dashboardStatistic.setNumber(classRepository.countClass());
        dashboardStatistic.setName("Total Class");
        return dashboardStatistic;
    }

    public List<SubscriptionStatistics> getMonthlySubscriptions(int val) {
        String year = String.valueOf(val);
        try {
            String jsonArrayString = studentRepository.getMonthlyCountsByYear(year);
            ObjectMapper mapper = new ObjectMapper();

            List<SubscriptionStatistics> subscriptionStatistics = mapper.readValue(
                    jsonArrayString, new TypeReference<>() {
                    }
            );


            Set<Integer> existingMonths = new HashSet<>();
            for (SubscriptionStatistics statistics : subscriptionStatistics) {
                existingMonths.add(Integer.parseInt(statistics.getMonth()));
            }
            for (int i = 1; i <= 12; i++) {
                if (!existingMonths.contains(i)) {
                    SubscriptionStatistics statistics = new SubscriptionStatistics();
                    statistics.setYear(year);
                    statistics.setMonth(String.format("%02d", i));
                    statistics.setNumber(0);
                    subscriptionStatistics.add(statistics);
                }
            }
            subscriptionStatistics.sort(Comparator.comparing(SubscriptionStatistics::getYear)
                    .thenComparing(SubscriptionStatistics::getMonth, Comparator.comparingInt(Integer::parseInt)));

            return subscriptionStatistics;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<CourseListResponseDto> filterCoursesByTitle(String title) {
        List<Course> courses = courseRepository.findByTitle(title);
        return getCourseListResponseDtos(courses);
    }

    private List<CourseListResponseDto> getCourseListResponseDtos(List<Course> courses) {
        List<CourseListResponseDto> courseList = new ArrayList<>();
        for (Course course : courses) {
            CourseListResponseDto courseDto = new CourseListResponseDto();
//            String imageUrl = ServletUriComponentsBuilder.fromHttpUrl("")
//                    .path(course.getId().toString())
//                    .toUriString();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            courseDto.setTitle(course.getTitle());
            courseDto.setDescription(course.getDescription());
            int classNumber = getClassesNumber(course);
            int studentNumber = getStudentNumberOfCourse(course);
            courseDto.setClassNumber(classNumber);
            courseDto.setStudentNumber(studentNumber);
            courseDto.setImageUrl("imageUrl");
            courseList.add(courseDto);
        }
        return courseList;
    }
}
