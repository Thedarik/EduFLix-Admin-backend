package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.SupportMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportMessageRepository extends JpaRepository<SupportMessages, Long> {
}
