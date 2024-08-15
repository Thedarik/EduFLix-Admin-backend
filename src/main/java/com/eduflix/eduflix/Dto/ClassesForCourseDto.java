package com.eduflix.eduflix.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClassesForCourseDto {
    private Long classId;
    private int studentNumber;
    private double classFee;
    private String className;
    private String days;
    private LocalDate clasTime;
    private String room;
}
