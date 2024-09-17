package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.ClassesForCourseDto;
import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Dto.CourseWithClassDto;
import com.eduflix.eduflix.Dto.CourseWithIdDto;
import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Image;
import com.eduflix.eduflix.Repository.ClassesRepository;
import com.eduflix.eduflix.Repository.CourseRepository;
import com.eduflix.eduflix.Repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final ClassesRepository classesRepository;
    private final ImageRepository imageRepository;


    public boolean exists(String title) {
        return courseRepository.existsByTitle(title);
    }

    public boolean exists(Long id) {
        return courseRepository.existsById(id);
    }

    public Course saveNewCourse(CourseDto courseDto, MultipartFile image) throws IOException {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setTitle(courseDto.getTitle());

        image.getBytes();
        Image image11 = new Image();
        image11.setData(image.getBytes());
        course.setImage(image11);

        return courseRepository.save(course);
    }

    public List<CourseDto> findByFilter(String title) {
        List<Course> courses = courseRepository.findByTitle(title);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setTitle(course.getTitle());
            courseDto.setDescription(course.getDescription());
            courseDto.setName(course.getName());
//            courseDto.setImage(course.getImage().getData());
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }

    public CourseWithClassDto getCourseWithClasses(Long id) {
        Course course = courseRepository.findById(id).get();
        List<Classes> classes = classesRepository.findAllByCourse(course);
        return CourseWithClassDto.builder()
                .courseId(course.getId())
                .name(course.getName())
                .title(course.getTitle())
                .description(course.getDescription())
                .classesForCourseDtoList(parseClassesToDto(classes))
                .build();
    }

    private List<ClassesForCourseDto> parseClassesToDto(List<Classes> classes) {
        return classes.stream()
                .map(clas -> new ClassesForCourseDto(
                        clas.getId(),
                        clas.getStudentNumber(),
                        clas.getClassFee(),
                        clas.getName(),
                        clas.getDays(),
                        clas.getClassTime()))
                .collect(Collectors.toList());
    }

    public CourseWithIdDto getCourseForUpdate(Long id) {
        Course course = courseRepository.findById(id).get();
        return CourseWithIdDto.builder()
                .courseId(course.getId())
                .name(course.getName())
                .title(course.getTitle())
                .description(course.getDescription())
                .build();
    }

    @Transactional
    public void updateCourse(CourseWithIdDto newObj, Long id) {
        Course course = courseRepository.findById(id).get();
        course.setName(newObj.getName() != null ? newObj.getName() : course.getName());
        course.setTitle(newObj.getTitle() != null ? newObj.getTitle() : course.getTitle());
        course.setDescription(newObj.getDescription() != null ? newObj.getDescription() : course.getDescription());
        courseRepository.save(course);

    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).get();
    }
}
