package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Classes;
import com.eduflix.eduflix.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    List<Classes> findAllByCourse(Course course);
    @Query("select count(*) from Classes where course.id=:id")
    int countClasses(@Param("id") Long id);

    @Query("""
            SELECT COALESCE(SUM(a.studentNumber), 0)
            FROM Classes a
            WHERE a.course.id=:id""")
    int countStudents(@Param("id") Long id);
}
