package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResultDto {
    private double result;
    private Student student;

}
