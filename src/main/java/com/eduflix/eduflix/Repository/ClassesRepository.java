package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("select count(*) from Classes where course.id=:id")
    int countClasses(@Param("id") Long id);

    @Query("""
            SELECT COALESCE(SUM(a.studentNumber), 0)
            FROM Classes a
            WHERE a.course.id=:id""")
    int countStudents(@Param("id") Long id);
}
