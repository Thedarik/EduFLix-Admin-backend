package com.eduflix.eduflix.Entity;

import com.eduflix.eduflix.Enum.Gender;
import com.eduflix.eduflix.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Size(min = 7)
    @Column(unique = true, nullable = false)
    public String username;
    @Size(min = 7)
    @Column(unique = true, nullable = false)
    public String password;
    @Enumerated(EnumType.STRING)
    public UserRole role;
    @Enumerated(EnumType.STRING)
    public Gender gender;
    public Boolean enabled = false;
    public Boolean locked = false;
    public LocalDate createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }
}
