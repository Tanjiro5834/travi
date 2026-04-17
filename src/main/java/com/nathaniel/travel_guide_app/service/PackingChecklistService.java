package com.nathaniel.travel_guide_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.dto.DTOs.PackingCategoryDTO;
import com.nathaniel.travel_guide_app.entity.PackingChecklistItem;
import com.nathaniel.travel_guide_app.repository.PackingChecklistItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackingChecklistService {
    private final PackingChecklistItemRepository packingChecklistItemRepository;

    public List<PackingChecklistItem> getByCountryId(Long countryId){
        return packingChecklistItemRepository.findByCountryIdOrderByCategoryAscItemNameAsc(countryId);
    }

    public List<PackingCategoryDTO> getGroupedByCategory(Long countryId){
        List<PackingChecklistItem> items = packingChecklistItemRepository
        .findByCountryIdOrderByCategoryAscItemNameAsc(countryId);
        
        Map<String, List<PackingChecklistItem>> grouped = new HashMap<>();
        for(PackingChecklistItem item : items){
            String category = item.getCategory();
            grouped.computeIfAbsent(category, k -> new ArrayList<>()).add(item);
        }

        List<PackingCategoryDTO> result = new ArrayList<>();
        for(Map.Entry<String, List<PackingChecklistItem>> entry : grouped.entrySet()){
            PackingCategoryDTO dto = new PackingCategoryDTO();
            dto.setCategory(entry.getKey());
            dto.setItems(entry.getValue());
            result.add(dto);
        }
        return result;
    }
}
