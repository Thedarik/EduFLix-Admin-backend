package com.eduflix.eduflix.mappers;

import com.eduflix.eduflix.Dto.CertificateDto;
import com.eduflix.eduflix.Entity.Certificate;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CertificateMapper {
    Certificate toEntity(CertificateDto certificateDto);

    CertificateDto toDto(Certificate certificate);
}