package com.eduflix.eduflix.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CertificateResponseDto {
    private String name;
    private String title;
    private String description;
    private byte[] file;
}
