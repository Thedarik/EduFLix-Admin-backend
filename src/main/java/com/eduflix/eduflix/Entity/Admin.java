package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "admin")
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private String firstname;
    @JoinColumn(nullable = false)
    private String lastname;
    @JoinColumn(unique = true, nullable = false)
    private String contact;
    @JoinColumn(unique = true, nullable = false)
    private String email;
    @OneToOne(optional = false)
    private Users users;
}
