package com.eduflix.eduflix.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SupportMessageDto {
    @NotEmpty(message = "cannot be empty!")
    private Long id;
    @NotEmpty(message = "cannot be empty!")
    private String title;
    @NotEmpty(message = "cannot be empty!")
    private String message;
}
