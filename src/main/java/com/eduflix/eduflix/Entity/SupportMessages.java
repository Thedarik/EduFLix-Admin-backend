package com.eduflix.eduflix.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "support_message")
public class SupportMessages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String message;
    @JoinColumn(nullable = false)
    private LocalDateTime sentAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    public SupportMessages() {

    }
}
