package com.nathaniel.travel_guide_app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.dto.request.CountryRequest;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.entity.Country;
import com.nathaniel.travel_guide_app.mapper.CountryMapper;
import com.nathaniel.travel_guide_app.repository.CountryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryResponse> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryResponse> responseList = new java.util.ArrayList<>(countries.size());

        for(Country country : countries){
            country.setViewCount(country.getViewCount() + 1);
            countryRepository.save(country);

            CountryResponse dto = countryMapper.mapToResponse(country);
            dto.setId(country.getId());
            dto.setName(country.getName());
            dto.setUpdatedAt(java.time.LocalDateTime.now());
            
            responseList.add(dto);
        }

        return responseList;
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

    public CountryResponse createCountry(CountryRequest request){
        if(countryRepository.findByName(request.getName()).isPresent()){
            throw new RuntimeException("Country already exists");
        }

        Country country = new Country();
        country.setName(request.getName());
        country.setOverview(request.getOverview());
        country.setFlagUrl(request.getFlagUrl());
        country.setViewCount(0);

        Country saved = countryRepository.save(country);

        return countryMapper.mapToResponse(saved);
    }

    @Transactional
    public CountryResponse updateCoutry(Long id, CountryRequest request){
        Country country = countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Country not found"));

        country.setName(request.getName());
        country.setOverview(request.getOverview());
        country.setFlagUrl(request.getFlagUrl());

        Country updated = countryRepository.save(country);

        return countryMapper.mapToResponse(updated);
    }

    public void deleteCountry(Long id){
        if(!countryRepository.existsById(id)){
            throw new RuntimeException("Country not found");
        }
        countryRepository.deleteById(id);
    }
}
