package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Admin;
import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {
    @NotEmpty(message = "cannot be empty!")
    private Student student;
    private Admin admin;
    @NotEmpty(message = "cannot be empty!")
    private Course course;
}
