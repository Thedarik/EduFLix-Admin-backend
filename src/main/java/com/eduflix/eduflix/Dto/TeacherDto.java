package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Enum.Gender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class TeacherDto {
    @NotEmpty(message = "cannot be empty!")
    private String firstname;
    @NotEmpty(message = "cannot be empty!")
    private String lastname;
    @NotEmpty(message = "cannot be empty!")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "invalid email format")
    private String email;
    @NotEmpty(message = "cannot be empty!")
    @Pattern(regexp = "^\\+9989\\d{8}$", message = "contact phone must be in format +9989xxxxxxxx")
    private String contact;
    @NotNull(message = "Gender cannot be null!")
    private Gender gender;
    @NotNull(message = "Hired date cannot be null!")
    private LocalDate hiredAt;
}
