package com.eduflix.eduflix.Entity;

import com.eduflix.eduflix.Enum.Gender;
import com.eduflix.eduflix.Enum.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users implements Serializable {
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

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    public Boolean enabled = false;
    public Boolean locked = false;
    public LocalDate createdAt;
}
