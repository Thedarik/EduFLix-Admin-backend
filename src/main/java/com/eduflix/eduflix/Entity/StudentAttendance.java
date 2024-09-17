package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "student_attendance")
public class StudentAttendance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    //for now i did not add class entity, since student entity already has class entity in it
    private Boolean atLesson;
    private LocalDate date;
}
