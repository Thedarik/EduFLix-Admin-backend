package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByEmailOrContact(String email, String contact);

    @Query("select count(*) from Teacher")
    Integer countTeachers();
}
