package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Entity.Image;
import com.eduflix.eduflix.Repository.CourseRepository;
import com.eduflix.eduflix.Repository.ImageRepository;
import com.eduflix.eduflix.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final UsersRepository usersRepository;
    private final CourseRepository courseRepository;

    public HttpEntity<Image> uploadImage(MultipartFile file, Long userId, Long courseId) throws IOException {

        Image image = new Image();

        if (courseId == null) image.setUsers(usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        else image.setCourse(courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found")));

        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());

        return ResponseEntity.ok(imageRepository.save(image));
    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
