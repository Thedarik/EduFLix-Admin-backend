package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Setter
@Getter
//todo: this entity is used to get schedule of lessons and to know
// whether any room is empty for new lesson
public class LessonSchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Classes classes;
    @ManyToOne
    private Room room;
}
