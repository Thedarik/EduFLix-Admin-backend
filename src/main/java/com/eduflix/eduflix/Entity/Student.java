package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String parentContact;
    @Column(nullable = false)
    private LocalDate enrollmentDate;
    @Column(nullable = false)
    private double payStatus;
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Users users;
    @OneToMany
    private Set<Classes> classes;
}
