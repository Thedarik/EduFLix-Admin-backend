package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.ExamResultResponseDto;
import com.eduflix.eduflix.Service.ExamResultService;
import com.eduflix.eduflix.Service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@AllArgsConstructor
public class ExamResultController {

    private final ExamResultService examResultService;
    private final StudentService studentService;

    @GetMapping("/results")
    public ResponseEntity<List<ExamResultResponseDto>> getExamResults(@RequestParam("studentId") Long studentId) {
        if (studentService.exists(studentId)) {
            return ResponseEntity.ok(examResultService.getResultsByStudentId(studentId));
        } else
            return ResponseEntity.notFound().build();
    }
}
