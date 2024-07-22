package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;


    public boolean exists(String title) {
        return courseRepository.existsByTitle(title);
    }

    public List<CourseDto> findByFilter(String title) {
        List<Course> courses = courseRepository.findByTitle(title);
        List<CourseDto> courseDtos = new ArrayList<>();
        for (Course course : courses) {
            CourseDto courseDto = new CourseDto();
            courseDto.setTitle(course.getTitle());
            courseDto.setDescription(course.getDescription());
            courseDto.setName(course.getName());
            courseDto.setImage(course.getImage().getData());
            courseDtos.add(courseDto);
        }
        return courseDtos;
    }
}
