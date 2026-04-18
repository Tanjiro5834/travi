package com.nathaniel.travel_guide_app.mapper;

import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.dto.admin_dto.response.AdminCountryResponse;
import com.nathaniel.travel_guide_app.dto.response.CountryDetailResponse;
import com.nathaniel.travel_guide_app.dto.response.CountryResponse;
import com.nathaniel.travel_guide_app.entity.Country;

@Component
public class CountryMapper {
    public CountryResponse mapToResponse(Country entity) {
        CountryResponse dto = new CountryResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRegion(entity.getRegion());
        dto.setSubRegion(entity.getSubRegion());
        dto.setCode(entity.getCode());
        dto.setCapital(entity.getCapital());
        dto.setFlagUrl(entity.getFlagUrl());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public AdminCountryResponse mapToAdminResponse(Country entity) {
        AdminCountryResponse dto = new AdminCountryResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setRegion(entity.getRegion());
        dto.setSubRegion(entity.getSubRegion());
        dto.setCode(entity.getCode());
        dto.setCapital(entity.getCapital());
        dto.setCurrency(entity.getCurrency());
        dto.setLanguage(entity.getLanguage());
        dto.setTimeZone(entity.getTimeZone());
        dto.setBestTimeToVisit(entity.getBestTimeToVisit());
        dto.setSafetyLevel(entity.getSafetyLevel());
        dto.setFlagUrl(entity.getFlagUrl());
        dto.setOverview(entity.getOverview());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public CountryDetailResponse mapToDetailResponse(Country entity) {
        CountryDetailResponse dto = new CountryDetailResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSlug(entity.getSlug());
        dto.setRegion(entity.getRegion());
        dto.setSubRegion(entity.getSubRegion());
        dto.setCode(entity.getCode());
        dto.setCapital(entity.getCapital());
        dto.setCurrency(entity.getCurrency());
        dto.setLanguage(entity.getLanguage());
        dto.setTimeZone(entity.getTimeZone());
        dto.setBestTimeToVisit(entity.getBestTimeToVisit());
        dto.setSafetyLevel(entity.getSafetyLevel());
        dto.setFlagUrl(entity.getFlagUrl());
        dto.setOverview(entity.getOverview());
        return dto;
    }
}
