package com.eduflix.eduflix.Dto;

import com.eduflix.eduflix.Entity.Image;

public record CourseGetDTO(Long id, String name, String description, String teacher, Image image) {
}
