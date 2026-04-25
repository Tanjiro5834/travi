package com.nathaniel.travel_guide_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nathaniel.travel_guide_app.dto.admin_dto.request.BudgetGuideRequest;
import com.nathaniel.travel_guide_app.entity.BudgetGuide;
import com.nathaniel.travel_guide_app.service.BudgetGuideService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetGuideController {
    private final BudgetGuideService budgetService;

    @GetMapping("/country/{countryId}")
    public ResponseEntity<BudgetGuide> getByCountry(@PathVariable Long countryId) {
        BudgetGuide data = budgetService.getByCountryId(countryId);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(data);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BudgetGuideRequest request) {
        return ResponseEntity.ok(budgetService.create(request));
    }

    @PutMapping("/country/{countryId}")
    public ResponseEntity<?> update(
        @PathVariable Long countryId,
        @RequestBody BudgetGuideRequest request
    ) {
        return ResponseEntity.ok(budgetService.update(countryId, request));
    }

    @GetMapping("/test")
    public String test() {
        return "BUDGET OK";
    }
}