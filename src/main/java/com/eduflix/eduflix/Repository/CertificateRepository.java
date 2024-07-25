package com.eduflix.eduflix.Repository;

import com.eduflix.eduflix.Entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByTitleContainingIgnoreCase(String title);
}