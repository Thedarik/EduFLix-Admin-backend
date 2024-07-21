package com.eduflix.eduflix.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenDecodeDTO {
    private String username;
    private List<SimpleGrantedAuthority> role;
}
