package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Boolean existsByTitle(String title);
    List<Course> findByTitle(String title);
}
