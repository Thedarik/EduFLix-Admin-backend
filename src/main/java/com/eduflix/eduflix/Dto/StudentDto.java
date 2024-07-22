package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Enum.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    @NotEmpty(message = "cannot be empty!")
    private String firstName;
    @NotEmpty(message = "cannot be empty!")
    private String lastName;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 6)
    private String email;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 9)
    private String phone;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 9)
    private String parentContact;
    @NotEmpty(message = "cannot be empty!")
    private LocalDate enrollmentDate;
    @NotEmpty(message = "cannot be empty!")
    private double payStatus;
    @NotEmpty(message = "cannot be empty!")
    private Gender gender;
    private Set<Classes> classes;
}
