package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.CourseDto;
import com.eduflix.eduflix.Service.CourseService;
import com.eduflix.eduflix.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/course")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @GetMapping("/filter")
    public ResponseEntity<List<CourseDto>> filter(@RequestParam String title) {
        if (courseService.exists(title)) {
            return ResponseEntity.ok(courseService.findByFilter(title));
        } else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/my-courses")
    public ResponseEntity<Set<CourseDto>> getMyCourses(@RequestParam("studentId") Long studentId) {
        if (studentService.exists(studentId)) {
            return ResponseEntity.ok(studentService.findByStudentId(studentId));
        } else
            return ResponseEntity.notFound().build();
    }
}
