package com.nathaniel.travel_guide_app.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nathaniel.travel_guide_app.dto.response.SavedDestinationResponse;
import com.nathaniel.travel_guide_app.service.SavedDestinationService;
import com.nathaniel.travel_guide_app.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/saved-destinations")
@RequiredArgsConstructor
public class SavedDestinationController {
    private final SavedDestinationService savedService;
    private final UserService userService; // To get the current userId

    @PostMapping("/{countryId}")
    public ResponseEntity<SavedDestinationResponse> save(@PathVariable Long countryId) {
        Long userId = userService.getCurrentUser().getId();
        return ResponseEntity.ok(savedService.saveDestination(userId, countryId));
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<Void> remove(@PathVariable Long countryId) {
        Long userId = userService.getCurrentUser().getId();
        savedService.removeDestination(userId, countryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SavedDestinationResponse>> getMyList() {
        Long userId = userService.getCurrentUser().getId();
        return ResponseEntity.ok(savedService.getSavedDestinations(userId));
    }
}