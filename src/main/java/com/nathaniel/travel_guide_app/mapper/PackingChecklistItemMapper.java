package com.nathaniel.travel_guide_app.mapper;

import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.dto.request.PackingChecklistItemRequest;
import com.nathaniel.travel_guide_app.dto.response.PackingChecklistItemResponse;
import com.nathaniel.travel_guide_app.entity.Country;
import com.nathaniel.travel_guide_app.entity.PackingChecklistItem;

@Component
public class PackingChecklistItemMapper {
    
    public PackingChecklistItem toEntity(PackingChecklistItemRequest request, Country country) {
        PackingChecklistItem entity = new PackingChecklistItem();
        entity.setCategory(request.getCategory());
        entity.setItemName(request.getItemName());
        entity.setEssential(request.getEssential());
        entity.setNote(request.getNote());
        entity.setCountry(country);
        return entity;
    }
    
    public PackingChecklistItemResponse mapToResponse(PackingChecklistItem entity) {
        PackingChecklistItemResponse dto = new PackingChecklistItemResponse();
        dto.setCountryId(entity.getId());
        dto.setCountry(entity.getCountry());
        dto.setCategory(entity.getCategory());
        dto.setItemName(entity.getItemName());
        dto.setEssential(entity.getEssential());
        dto.setNote(entity.getNote());
        return dto;
    }
}