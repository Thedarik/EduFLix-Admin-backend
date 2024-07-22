package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Entity.Image;
import com.eduflix.eduflix.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public HttpEntity<Image> uploadImage(@RequestParam("image") MultipartFile file, @RequestBody Long userId, @RequestBody Long courseId) {
        try {
            return imageService.uploadImage(file, userId, courseId);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{imageId}")
    public HttpEntity<Image> getImage(@PathVariable Long imageId) {
        Image image = imageService.getImage(imageId);
        if (image != null) {
            return new ResponseEntity<>(image, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
