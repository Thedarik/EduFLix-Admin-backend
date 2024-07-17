package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDto {
    @NotEmpty(message = "cannot be empty!")
    private double amount;
    @NotEmpty(message = "cannot be empty!")
    private Boolean status;
    @NotEmpty(message = "cannot be empty!")
    private Date date;
    @NotEmpty(message = "cannot be empty!")
    private Student student;
}
