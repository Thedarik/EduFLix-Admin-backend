package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Dto.CertificateDto;
import com.eduflix.eduflix.Entity.Certificate;
import com.eduflix.eduflix.Service.CertificateService;
import com.eduflix.eduflix.generator.PdfGenerator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService certificateService;

    @GetMapping
    public HttpEntity<?> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @SneakyThrows
    @PostMapping
    public HttpEntity<?> saveCertificate(@RequestBody CertificateDto certificateDto, @RequestParam MultipartFile file) {
        if (file.isEmpty() || file.getBytes().length == 0)
            return ResponseEntity.badRequest().body("image file is empty or no chosen");
        return certificateService.saveNewCertificate(certificateDto, file);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getChosenCertificate(@PathVariable Long id) {
        return certificateService.getCertificate(id);
    }

    @GetMapping("/search")
    public List<Certificate> getCertificatesByTitle(@RequestParam String title) {
        return certificateService.getCertificatesByTitle(title);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadCertificatePdf(@PathVariable Long id) {
        Optional<Certificate> certificateOptional = certificateService.getCertificateById(id);
        if (certificateOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Certificate certificate = certificateOptional.get();
        byte[] pdfBytes;
        try {
            pdfBytes = PdfGenerator.generateCertificatePdf(certificate);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + certificate.getName() + ".pdf\"");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                .body(pdfBytes);

    }
}
