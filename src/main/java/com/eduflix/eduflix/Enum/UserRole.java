package com.eduflix.eduflix.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_STUDENT,
    ROLE_TEACHER,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
