package com.nathaniel.travel_guide_app.service;

import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.entity.BudgetGuide;
import com.nathaniel.travel_guide_app.repository.BudgetGuideRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetGuideService {
    private final BudgetGuideRepository budgetGuideRepository;

    public BudgetGuide getByCountryId(Long countryId){
        return budgetGuideRepository.findByCountryId(countryId)
        .orElseThrow(() -> new RuntimeException("Budget guide not found for country ID: " + countryId));
    }
}
