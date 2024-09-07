package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Student;
import com.eduflix.eduflix.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Boolean existsByEmail(String email);

    Student findByUsers(Users user);

    @Query("select count(*) from Student")
    int countStudents();

    @Query(value = """
            WITH monthly_counts AS (
                SELECT
                    TO_CHAR(created_at, 'YYYY') AS year,
                    TO_CHAR(created_at, 'MM') AS month,
                    COUNT(*) AS number
                FROM
                    users
                join student s on s.users_id = users.id
                WHERE
                    TO_CHAR(created_at, 'YYYY') = :year
                GROUP BY
                    TO_CHAR(created_at, 'YYYY'),
                    TO_CHAR(created_at, 'MM')
            )
            SELECT json_agg(json_build_object(
                    'year', year,
                    'month', month,
                    'number', number
                            )) AS result
            FROM monthly_counts
            """, nativeQuery = true)
    String getMonthlyCountsByYear(String year);
}
