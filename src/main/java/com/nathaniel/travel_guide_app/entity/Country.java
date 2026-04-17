package com.nathaniel.travel_guide_app.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    private String code; // JP, KR, SG
    private String capital;
    private String currency;
    private String language;
    private String timeZone;
    private String bestTimeToVisit;
    private String safetyLevel;
    private String flagUrl;

    @Column(length = 2000)
    private String overview;

    @Column(columnDefinition = "int default 0")
    private int viewCount;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}