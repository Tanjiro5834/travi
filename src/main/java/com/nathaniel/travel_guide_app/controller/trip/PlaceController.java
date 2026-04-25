package com.nathaniel.travel_guide_app.controller.trip;

import java.util.List;
 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import com.nathaniel.travel_guide_app.entity.Place;
import com.nathaniel.travel_guide_app.dto.request.trip.PlaceRequest;
import com.nathaniel.travel_guide_app.dto.response.trip.PlaceResponse;
import com.nathaniel.travel_guide_app.service.trip.PlaceService;
 
import lombok.RequiredArgsConstructor;
 
@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {
 
    private final PlaceService placeService;
 
    @GetMapping("/{id}")
    public ResponseEntity<PlaceResponse> getById(@PathVariable Long id) {
        Place place = placeService.getById(id);
        if (place == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(placeService.getResponseById(id));
    }
 
    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<PlaceResponse>> getByCountry(@PathVariable Long countryId) {
        return ResponseEntity.ok(placeService.getByCountryResponse(countryId));
    }
 
    @GetMapping("/country/{countryId}/published")
    public ResponseEntity<List<PlaceResponse>> getPublishedByCountry(@PathVariable Long countryId) {
        return ResponseEntity.ok(placeService.getPublishedResponsesByCountry(countryId));
    }
 
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PlaceResponse>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(placeService.getResponsesByCategory(categoryId));
    }
 
    @GetMapping("/featured")
    public ResponseEntity<List<PlaceResponse>> getFeatured() {
        return ResponseEntity.ok(placeService.getFeaturedResponses());
    }
 
    @PostMapping("/admin")
    public ResponseEntity<PlaceResponse> create(@RequestBody PlaceRequest request) {
        return ResponseEntity.ok(placeService.createPlace(request));
    }
 
    @PutMapping("/admin/{id}")
    public ResponseEntity<PlaceResponse> update(
            @PathVariable Long id,
            @RequestBody PlaceRequest request) {
        return ResponseEntity.ok(placeService.updatePlace(id, request));
    }
 
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        placeService.deletePlace(id);
        return ResponseEntity.noContent().build();
    }
 
    @PatchMapping("/admin/{id}/feature")
    public ResponseEntity<Place> feature(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.feature(id));
    }

    @PatchMapping("/admin/{id}/unfeature")
    public ResponseEntity<Place> unfeature(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.unfeature(id));
    }

    @PatchMapping("/admin/{id}/publish")
    public ResponseEntity<Place> publish(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.publish(id));
    }

    @PatchMapping("/admin/{id}/unpublish")
    public ResponseEntity<Place> unpublish(@PathVariable Long id) {
        return ResponseEntity.ok(placeService.unpublish(id));
    }
}