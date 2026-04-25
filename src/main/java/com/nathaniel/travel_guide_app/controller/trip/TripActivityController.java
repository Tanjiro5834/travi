package com.nathaniel.travel_guide_app.controller.trip;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nathaniel.travel_guide_app.dto.request.trip.TripActivityRequest;
import com.nathaniel.travel_guide_app.dto.response.trip.TripActivityResponse;
import com.nathaniel.travel_guide_app.entity.Place;
import com.nathaniel.travel_guide_app.entity.TripActivity;
import com.nathaniel.travel_guide_app.repository.Trip.PlaceRepository;
import com.nathaniel.travel_guide_app.service.trip.TripActivityService;

@RestController
@RequestMapping("/api/trip-activities")
@RequiredArgsConstructor
public class TripActivityController {
    private final TripActivityService service;
    private final PlaceRepository placeRepository;

    @PostMapping
    public ResponseEntity<TripActivityResponse> create(
        @RequestBody TripActivityRequest request
    ) {
        return ResponseEntity.ok(service.createTripActivity(request));
    }

    // UPDATE (THIS MATCHES YOUR FRONTEND)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Long id,
        @RequestBody TripActivityRequest request
    ) {
        Place place = placeRepository.findById(request.getPlaceId())
            .orElseThrow(() -> new RuntimeException("Place not found"));

        TripActivity activity = new TripActivity();
        activity.setPlace(place);
        activity.setStartTime(request.getStartTime());
        activity.setEndTime(request.getEndTime());
        activity.setNotes(request.getNotes());

        return ResponseEntity.ok(service.updateTripActivity(id, activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteTripActivity(id);
        return ResponseEntity.noContent().build();
    }
}