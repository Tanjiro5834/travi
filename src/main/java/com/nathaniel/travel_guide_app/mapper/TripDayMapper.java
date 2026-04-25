package com.nathaniel.travel_guide_app.mapper;

import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.dto.request.trip.TripDayRequest;
import com.nathaniel.travel_guide_app.dto.response.trip.TripDayResponse;
import com.nathaniel.travel_guide_app.entity.Trip;
import com.nathaniel.travel_guide_app.entity.TripDay;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TripDayMapper {
    private final TripActivityMapper tripActivityMapper;
    public TripDay toEntity(TripDayRequest request, Trip trip) {
        TripDay tripDay = new TripDay();
        tripDay.setTrip(trip);
        tripDay.setDayNumber(request.getDayNumber());
        tripDay.setDate(request.getDate());
        tripDay.setNotes(request.getNotes());
        return tripDay;
    }

    public TripDayResponse toResponse(TripDay tripDay) {
        TripDayResponse response = new TripDayResponse();

        response.setId(tripDay.getId());
        response.setTripId(tripDay.getTrip().getId());
        response.setDayNumber(tripDay.getDayNumber());
        response.setDate(tripDay.getDate());
        response.setNotes(tripDay.getNotes());
        response.setCreatedAt(tripDay.getCreatedAt());
        response.setUpdatedAt(tripDay.getUpdatedAt());

        // 🔥 THIS IS THE FIX
        response.setActivities(
            tripDay.getActivities()
                .stream()
                .map(tripActivityMapper::toResponse)
                .toList()
        );

        return response;
    }

    public void updateEntity(TripDay tripDay, TripDayRequest request, Trip trip) {
        tripDay.setTrip(trip);
        tripDay.setDayNumber(request.getDayNumber());
        tripDay.setDate(request.getDate());
        tripDay.setNotes(request.getNotes());
    }
}