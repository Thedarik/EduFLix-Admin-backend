package com.eduflix.eduflix.AdminPanel.Dto;

import lombok.Data;

@Data
public class CourseListResponseDto {
    private Long id;
    private String name;
    private String description;
    private String title;
    private int classNumber;
    private int studentNumber;
    private String imageUrl;
}
