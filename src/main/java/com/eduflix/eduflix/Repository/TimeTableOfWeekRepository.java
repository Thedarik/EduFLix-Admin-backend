package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.TimeTableOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableOfWeekRepository extends JpaRepository<TimeTableOfWeek, Long> {
}
