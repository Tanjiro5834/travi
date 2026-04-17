package com.nathaniel.travel_guide_app.mapper;

import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.entity.Country;

@Component
public class CountryMapper {
    public CountryResponse mapToResponse(Country entity) {
        CountryResponse dto = new CountryResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRegion(null); // Not present in Entity
        dto.setFlagEmoji(null); // Not present in Entity (only flagUrl)
        dto.setStatus(null); // Not present in Entity
        dto.setUpdatedAt(java.time.LocalDateTime.now()); 
        return dto;
    }
}
