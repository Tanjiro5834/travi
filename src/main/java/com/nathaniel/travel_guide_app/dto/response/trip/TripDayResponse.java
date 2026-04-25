package com.nathaniel.travel_guide_app.dto.response.trip;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TripDayResponse {
    private Long id;
    private Long tripId;
    private Integer dayNumber;
    private LocalDate date;
    private String notes;
    private List<TripActivityResponse> activities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
