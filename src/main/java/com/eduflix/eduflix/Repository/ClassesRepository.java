package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    List<Classes> findAllByCourse(Course course);
}
