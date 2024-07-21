package com.eduflix.eduflix.mappers;

import com.eduflix.eduflix.Dto.UserDto;
import com.eduflix.eduflix.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsersMapper {
    Users toEntity(UserDto userDto);

    UserDto toDto(Users users);
}
