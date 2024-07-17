package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "fee")
public class Fee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false)
    private Boolean status;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne(optional = false)
    private Student student;

}
