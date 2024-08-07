package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.ExamResult;
import com.eduflix.eduflix.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    List<ExamResult> findByStudent(Student student);
}
