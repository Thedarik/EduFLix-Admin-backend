package com.eduflix.eduflix.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class Security {
    private final CustomUserDetailsService customUserDetailsService;
    private final String[] SWAGGER_URLS = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Filter filter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(SWAGGER_URLS).permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/course/filter",
                                "/api/course/my-courses").permitAll()
                        .requestMatchers("/api/user").permitAll()
                        .requestMatchers("/api/course/add").permitAll()
                        .requestMatchers("/api/course/image").permitAll()
//                        .requestMatchers("/api/image/upload").permitAll()
                        // only admin and student should get profile and exam data of students
                        .requestMatchers("/api/profile/get").hasAnyRole("STUDENT", "ADMIN")
                        .requestMatchers("/api/profile/image/**").hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers("/api/exam/results").hasAnyRole("STUDENT", "ADMIN")
                        // only students should send support message to admin
                        .requestMatchers("/api/support/from-student").hasRole("STUDENT")
                        .requestMatchers("/ws/**","/chat/**").hasAnyRole("STUDENT", "ADMIN")
                        // images should be uploaded in admin(for courses), student and teacher panells
                        .requestMatchers("/api/image/**").hasAnyRole("ADMIN", "STUDENT", "TEACHER")
                        .requestMatchers("/api/student/create").hasRole("ADMIN")
                        .requestMatchers("/api/dashboard/all-course",
                                "/api/dashboard/total-signing",
                                "/api/dashboard/monthly-signing",
                                "/api/dashboard/total-course",
                                "/api/dashboard/total-class",
                                "/api/dashboard/mon-subsc").permitAll()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(m -> m.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(authenticationProvider());
    }
}
