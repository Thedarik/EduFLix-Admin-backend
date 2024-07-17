package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double paid;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Fee fee;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Student student;

}
