package com.eduflix.eduflix.controller;

import com.eduflix.eduflix.Entity.Users;
import com.eduflix.eduflix.Repository.UsersRepository;
import com.eduflix.eduflix.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refresh/token")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final JwtUtil jwtUtil;
    private final UsersRepository usersRepository;

    @GetMapping
    public String refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return jwtUtil.generateToken(users);
    }
}
