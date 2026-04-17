package com.nathaniel.travel_guide_app.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "saved_destinations",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "country_id"})
)
@Getter
@Setter
public class SavedDestination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    private LocalDateTime savedAt;
}
