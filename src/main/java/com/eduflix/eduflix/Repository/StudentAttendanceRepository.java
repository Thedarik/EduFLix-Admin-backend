package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
}
