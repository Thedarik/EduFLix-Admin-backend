package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    private Users users;

    @OneToOne(cascade = CascadeType.ALL)
    private Course course;

}