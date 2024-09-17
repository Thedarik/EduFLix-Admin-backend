package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "course")
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name; // ex: General English
    @Column(nullable = false)
    private String description;
    @JoinColumn(nullable = false)
    private String title; // ex: English
    @OneToOne
    private Image image;
    private LocalDateTime createdAt;
}
