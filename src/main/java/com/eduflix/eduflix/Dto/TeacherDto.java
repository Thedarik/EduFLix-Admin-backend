package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Course;
import com.eduflix.eduflix.Entity.Users;
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
public class TeacherDto {
    @NotEmpty(message = "cannot be empty!")
    private String firstname;
    @NotEmpty(message = "cannot be empty!")
    private String lastname;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 6)
    private String email;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 9)
    private String contact;
    @NotEmpty(message = "cannot be empty!")
    private LocalDate hiredAt;
    @NotEmpty(message = "cannot be empty!")
    private Users users;
    @NotEmpty(message = "cannot be empty!")
    private Set<Course> course;
}
