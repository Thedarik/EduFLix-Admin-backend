package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.StudentDto;
import com.eduflix.eduflix.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("create")
    public ResponseEntity<Object> create(@RequestBody StudentDto student) {

        if (studentService.existsByEmail(student.getEmail())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("already exists!");
        }
        else
            return ResponseEntity.ok(studentService.saveStudent(student));


    }
}
