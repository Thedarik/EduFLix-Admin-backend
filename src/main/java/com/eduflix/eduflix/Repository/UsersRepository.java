package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("""
            SELECT COUNT(*)
            FROM Users u
                     JOIN Student s ON u.id = s.users.id
            WHERE u.createdAt BETWEEN :begin AND :now
            """)
    int monthlyCountStudents(@Param("begin") LocalDate begin, @Param("now") LocalDate now);
}
