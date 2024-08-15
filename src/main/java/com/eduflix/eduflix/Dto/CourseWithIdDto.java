package com.eduflix.eduflix.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseWithIdDto {
    private Long courseId;
    @NotEmpty(message = "cannot be empty!")
    private String name;
    @NotEmpty(message = "cannot be empty!")
    private String description;
    @NotEmpty(message = "cannot be empty!")
    private String title;
}
