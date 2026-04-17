package com.nathaniel.travel_guide_app.service;

import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.entity.EntryRequirement;
import com.nathaniel.travel_guide_app.repository.EntryRequirementRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryRequirementService {
    private final EntryRequirementRepository entryRequirementRepository;

    public EntryRequirement getByCountryId(Long countryId){
        return entryRequirementRepository.findByCountryId(countryId)
        .orElseThrow(() -> new RuntimeException("Entry requirements not found for country ID: " + countryId));
    }
}
