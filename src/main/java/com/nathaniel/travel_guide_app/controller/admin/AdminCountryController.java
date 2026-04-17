package com.nathaniel.travel_guide_app.controller.admin;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.service.CountryService;

@RestController
@RequestMapping("/api/admin/countries")
public class AdminCountryController {
    private final CountryService countryService;

    public AdminCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponse> getAll() {
        return countryService.getAllCountries();
    }
}
