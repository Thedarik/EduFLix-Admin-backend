package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceDto {
    @NotEmpty(message = "cannot be empty!")
    private Boolean hasLesson;
    @NotEmpty(message = "cannot be empty!")
    private LocalDate date;
    @NotEmpty(message = "cannot be empty!")
    private Student student;
}
