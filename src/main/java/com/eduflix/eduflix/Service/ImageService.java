package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Entity.Image;
import com.eduflix.eduflix.Repository.ImageRepository;
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

    public HttpEntity<Image> uploadImage(MultipartFile file, Long userId) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        return ResponseEntity.ok(imageRepository.save(image));

    }

    public Image getImage(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
