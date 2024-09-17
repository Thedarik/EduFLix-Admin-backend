package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Teacher;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassesDto {
    @NotEmpty(message = "cannot be empty!")
    private int studentNumber;
    @NotEmpty(message = "cannot be empty!")
    private String name;
    @NotEmpty(message = "cannot be empty!")
    private double classFee;
    @NotEmpty(message = "cannot be empty!")
    private String days;
    @NotEmpty(message = "cannot be empty!")
    private String classTime;
    @NotEmpty(message = "cannot be empty!")
    private String room;
    @NotEmpty(message = "cannot be empty!")
    private Long teacherId;
    @NotEmpty(message = "cannot be empty!")
    private Long courseId;

}
