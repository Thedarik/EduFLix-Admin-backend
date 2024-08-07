package com.eduflix.eduflix.Dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ExamResultResponseDto {
    private Long studentId;
    private String courseName;
    private String className;
    private LocalDateTime examTime;
    private double result;
}
