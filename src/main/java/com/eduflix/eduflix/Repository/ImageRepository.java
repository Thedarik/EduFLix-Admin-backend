package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
