package com.nathaniel.travel_guide_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import com.nathaniel.travel_guide_app.enums.DestinationType;

@Entity
@Table(name = "destinations")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DestinationType type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isPublished = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}