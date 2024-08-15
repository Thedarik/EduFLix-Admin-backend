package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.ClassesDto;
import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @PostMapping("/create")
    public HttpEntity<?> addPost(@RequestBody ClassesDto classesDto) {
        try {
            Classes savedClass = classService.saveNewClass(classesDto);
            if (savedClass != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Class successfully created! Class: " + savedClass);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create class");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the class");
        }
    }

    @DeleteMapping("/del/{classId}")
    public HttpEntity<?> deleteClass(@PathVariable Long classId) {
        if (classService.exists(classId)) {
            classService.deleteClass(classId);
            return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
