package com.eduflix.eduflix.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificateDto {
    private String name;
    private String title;
    private String description;
}
