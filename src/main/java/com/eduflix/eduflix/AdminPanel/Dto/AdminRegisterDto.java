package com.eduflix.eduflix.AdminPanel.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminRegisterDto {
    @NotEmpty(message = "cannot be empty!")
    private String firstname;
    @NotEmpty(message = "cannot be empty!")
    private String lastname;
    @NotEmpty(message = "cannot be empty!")
    private String contact;
    @NotEmpty(message = "cannot be empty!")
    private String email;
    @NotEmpty(message = "cannot be empty!")
    private String username;
}
