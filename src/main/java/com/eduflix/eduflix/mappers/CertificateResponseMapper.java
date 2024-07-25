package com.eduflix.eduflix.mappers;

import com.eduflix.eduflix.Dto.CertificateResponseDto;
import com.eduflix.eduflix.Entity.Certificate;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CertificateResponseMapper {
    Certificate toEntity(CertificateResponseDto certificateResponseDto);

    CertificateResponseDto toDto(Certificate certificate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Certificate partialUpdate(CertificateResponseDto certificateResponseDto, @MappingTarget Certificate certificate);
}