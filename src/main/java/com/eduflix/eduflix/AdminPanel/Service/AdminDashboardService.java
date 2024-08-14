package com.eduflix.eduflix.AdminPanel.Service;

import com.eduflix.eduflix.AdminPanel.Dto.CourseListResponseDto;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Repository.ClassesRepository;
import com.eduflix.eduflix.Repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminDashboardService {

    private final CourseRepository courseRepository;
    private final ClassesRepository classRepository;
    public List<CourseListResponseDto> getAllCourseList() {
        List<Course> courses = courseRepository.findAll();
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

    private int getStudentNumberOfCourse(Course course) {
        return classRepository.countStudents(course.getId());
    }

    private int getClassesNumber(Course course) {
        return classRepository.countClasses(course.getId());
    }
}
