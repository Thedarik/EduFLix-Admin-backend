package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.CourseGetDTO;
import com.eduflix.eduflix.Dto.MasterDTO;
import com.eduflix.eduflix.Repository.CourseRepository;
import com.eduflix.eduflix.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {


    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public HttpEntity<List<MasterDTO>> getMastersList() {

        return ResponseEntity.ok(teacherRepository.findAll()
                .stream()
                .map(teacher -> new MasterDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname(), null))
                .toList());
    }

    public HttpEntity<List<CourseGetDTO>> getCoursesList() {

        return ResponseEntity.ok(courseRepository.findAll()
                .stream()
                .map(course -> new CourseGetDTO(course.getId(), course.getName(), course.getDescription(), null, null))
                .toList());
    }
}
