package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Fee;
import com.eduflix.eduflix.Entity.Student;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    @NotEmpty(message = "cannot be empty!")
    private double paid;
    @NotEmpty(message = "cannot be empty!")
    private String description;
    @NotEmpty(message = "cannot be empty!")
    private Fee fee;
    @NotEmpty(message = "cannot be empty!")
    private Date date;
    @NotEmpty(message = "cannot be empty!")
    private Student student;
}
