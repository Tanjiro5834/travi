package com.nathaniel.travel_guide_app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.entity.Country;
import com.nathaniel.travel_guide_app.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    public Country getCountryBySlug(String slug) {
        return countryRepository.findBySlug(slug).orElse(null);
    }

    public List<Country> searchCountries(String keyword) {
        return countryRepository.findByNameContainingIgnoreCase(keyword);
    }

    public Country getPopularCountry() {
        return countryRepository.findFirstByOrderByViewCountDesc()
        .orElseThrow(() -> new RuntimeException("No countries found"));
    }
}
