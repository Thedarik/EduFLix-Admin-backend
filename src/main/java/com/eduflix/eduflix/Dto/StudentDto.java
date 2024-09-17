package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Enum.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 6)
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "invalid email format")
    private String email;
    @NotEmpty(message = "cannot be empty!")
    @Pattern(regexp = "^\\+9989\\d{8}$", message = "phone must be in format +9989xxxxxxxx")
    private String phone;
    @NotEmpty(message = "cannot be empty!")
    @Pattern(regexp = "^\\+9989\\d{8}$", message = "parent contact must be in format +9989xxxxxxxx")
    private String parentContact;
    private LocalDate enrollmentDate;
    @NotEmpty(message = "cannot be empty!")
    private double payStatus;
    @NotEmpty(message = "cannot be empty!")
    private Gender gender;
    private Set<Classes> classes;
}
