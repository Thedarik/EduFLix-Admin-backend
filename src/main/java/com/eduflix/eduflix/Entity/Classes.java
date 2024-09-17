package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "class")
public class Classes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int studentNumber;
    @Column(nullable = false)
    private double classFee;
    @Column(nullable = false)
    private String days;
    @Column(nullable = false)
    private String classTime;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Course course;
    private LocalDateTime createdAt;
}
