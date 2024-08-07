package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByEmail(String email);

    Student findByUsers(Users user);
}
