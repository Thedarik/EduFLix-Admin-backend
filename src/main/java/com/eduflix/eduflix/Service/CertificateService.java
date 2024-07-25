package com.eduflix.eduflix.Service;

import com.eduflix.eduflix.Dto.CertificateDto;
import com.eduflix.eduflix.Dto.CertificateResponseDto;
import com.eduflix.eduflix.Entity.Certificate;
import com.eduflix.eduflix.Repository.CertificateRepository;
import com.eduflix.eduflix.mappers.CertificateMapper;
import com.eduflix.eduflix.mappers.CertificateResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificateResponseMapper certificateResponseMapper;
    private final CertificateMapper certificateMapper;

    public HttpEntity<?> getAllCertificates() {
        List<Certificate> certificates = certificateRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<CertificateResponseDto> certificateResponseDtos = new ArrayList<>();
        for (Certificate certificate : certificates) {
            CertificateResponseDto responseDto = certificateResponseMapper.toDto(certificate);
            certificateResponseDtos.add(responseDto);
        }
        return ResponseEntity.ok(certificateResponseDtos);
    }

    @Transactional
    @SneakyThrows
    public HttpEntity<?> saveNewCertificate(CertificateDto certificateDto, MultipartFile file) {
        Certificate certificate = certificateMapper.toEntity(certificateDto);
        certificate.setFile(file.getBytes());
        certificateRepository.save(certificate);
        return ResponseEntity.status(200).body("Certificate successful saved");
    }

    public HttpEntity<?> getCertificate(Long id) {
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException("certificate not found exception"));
        CertificateResponseDto responseDto = certificateResponseMapper.toDto(certificate);
        return ResponseEntity.ok(responseDto);
    }

    public List<Certificate> getCertificatesByTitle(String title) {
        return certificateRepository.findByTitleContainingIgnoreCase(title);
    }

    public Optional<Certificate> getCertificateById(Long id) {
        return certificateRepository.findById(id);
    }
}
