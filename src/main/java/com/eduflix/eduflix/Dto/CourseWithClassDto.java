package com.eduflix.eduflix.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseWithClassDto {
    private Long courseId;
    private String name;
    private String title;
    private String description;
    List<ClassesForCourseDto> classesForCourseDtoList;
}
