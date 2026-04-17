package com.nathaniel.travel_guide_app.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nathaniel.travel_guide_app.dto.DTOs.PackingCategoryDTO;
import com.nathaniel.travel_guide_app.entity.PackingChecklistItem;
import com.nathaniel.travel_guide_app.service.PackingChecklistService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/packing-checklists")
@RequiredArgsConstructor
public class PackingChecklistController {
    private final PackingChecklistService packingService;

    @GetMapping("/country/{countryId}")
    public ResponseEntity<List<PackingCategoryDTO>> getGrouped(@PathVariable Long countryId) {
        // Returns the hierarchical DTO structure created in service
        return ResponseEntity.ok(packingService.getGroupedByCategory(countryId));
    }

    @GetMapping("/country/{countryId}/raw")
    public ResponseEntity<List<PackingChecklistItem>> getRaw(@PathVariable Long countryId) {
        return ResponseEntity.ok(packingService.getByCountryId(countryId)); //
    }
}