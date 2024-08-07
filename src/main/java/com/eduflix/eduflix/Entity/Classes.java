package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "class")
public class Classes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int studentNumber;
    @Column(nullable = false)
    private double classFee;
    @JoinColumn
    private String className;
    @Column(nullable = false)
    private String days;
    @Column(nullable = false)
    private LocalDate clasTime;
    @Column(nullable = false)
    private String room;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Course course;
}
