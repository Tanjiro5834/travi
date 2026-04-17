package com.nathaniel.travel_guide_app.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nathaniel.travel_guide_app.dto.DTOs.PackingCategoryDTO;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.entity.BudgetGuide;
import com.nathaniel.travel_guide_app.entity.Country;
import com.nathaniel.travel_guide_app.entity.CultureGuideItem;
import com.nathaniel.travel_guide_app.entity.EntryRequirement;
import com.nathaniel.travel_guide_app.entity.LocalTip;
import com.nathaniel.travel_guide_app.service.BudgetGuideService;
import com.nathaniel.travel_guide_app.service.CountryService;
import com.nathaniel.travel_guide_app.service.CultureGuideService;
import com.nathaniel.travel_guide_app.service.EntryRequirementService;
import com.nathaniel.travel_guide_app.service.LocalTipService;
import com.nathaniel.travel_guide_app.service.PackingChecklistService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final CultureGuideService cultureService;
    private final LocalTipService tipService;
    private final PackingChecklistService packingService;
    private final BudgetGuideService budgetService;
    private final EntryRequirementService entryService;

    @GetMapping
    public List<CountryResponse> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @GetMapping("/{id}/culture")
    public Map<String, List<CultureGuideItem>> getCulture(@PathVariable Long id) {
        return cultureService.getByCountryIdGroupedByType(id);
    }

    @GetMapping("/{id}/tips")
    public Map<String, List<LocalTip>> getTips(@PathVariable Long id) {
        return tipService.getByCountryIdGroupedByCategory(id);
    }

    @GetMapping("/{id}/checklist")
    public List<PackingCategoryDTO> getChecklist(@PathVariable Long id) {
        return packingService.getGroupedByCategory(id);
    }

    @GetMapping("/{id}/budget")
    public BudgetGuide getBudget(@PathVariable Long id) {
        return budgetService.getByCountryId(id);
    }

    @GetMapping("/{id}/requirements")
    public EntryRequirement getRequirements(@PathVariable Long id) {
        return entryService.getByCountryId(id);
    }
}