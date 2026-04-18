package com.nathaniel.travel_guide_app.controller.admin;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.nathaniel.travel_guide_app.dto.admin_dto.request.CreateCountryRequest;
import com.nathaniel.travel_guide_app.dto.admin_dto.request.UpdateCountryRequest;
import com.nathaniel.travel_guide_app.dto.admin_dto.response.AdminCountryResponse;
import com.nathaniel.travel_guide_app.dto.response.CountryDetailResponse;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.service.CountryService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/countries")
@RequiredArgsConstructor
public class AdminCountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryResponse> getAll() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public AdminCountryResponse getById(@PathVariable Long id) {
        return countryService.getAdminCountryById(id);
    }

    @GetMapping("/{id}/details")
    public CountryDetailResponse getCountryDetailById(@PathVariable Long id) {
        return countryService.getCountryDetailById(id);
    }

    @PostMapping
    public CountryResponse create(@RequestBody CreateCountryRequest request) {
        return countryService.createCountry(request);
    }

    @PutMapping("/{id}")
    public CountryResponse update(@PathVariable Long id,
                                  @RequestBody UpdateCountryRequest request) {
        return countryService.updateCountry(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }
}