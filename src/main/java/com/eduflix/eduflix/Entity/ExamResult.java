package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "exam_result")
public class ExamResult implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "result %")
    private double result;
    @JoinColumn(name = "exam_time")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn
    private Classes classes;
    @ManyToOne
    private Student student;
}
