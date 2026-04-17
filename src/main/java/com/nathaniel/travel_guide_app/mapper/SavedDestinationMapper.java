package com.nathaniel.travel_guide_app.mapper;

import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.dto.response.SavedDestinationResponse;
import com.nathaniel.travel_guide_app.entity.SavedDestination;

@Component
public class SavedDestinationMapper {
    public SavedDestinationResponse mapToResponse(SavedDestination entity) {
        SavedDestinationResponse dto = new SavedDestinationResponse();
        dto.setId(entity.getId());
        dto.setCountryId(entity.getCountry().getId());
        dto.setCountryName(entity.getCountry().getName());
        dto.setCountrySlug(entity.getCountry().getSlug());
        dto.setFlagUrl(entity.getCountry().getFlagUrl());
        dto.setSavedAt(entity.getSavedAt());
        return dto;
    }
}
