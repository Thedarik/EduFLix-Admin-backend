package com.eduflix.eduflix.Dto;


import com.eduflix.eduflix.Enum.Gender;
import com.eduflix.eduflix.Enum.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 7)
    public String username;
    @NotEmpty(message = "cannot be empty!")
    @Size(min = 7)
    public String password;
    @NotEmpty(message = "cannot be empty!")
    public UserRole role;
    @NotEmpty(message = "cannot be empty!")
    public Gender gender;
    public Boolean enabled;
}
