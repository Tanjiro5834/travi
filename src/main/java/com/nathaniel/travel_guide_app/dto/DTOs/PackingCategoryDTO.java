package com.nathaniel.travel_guide_app.dto.DTOs;

import java.util.List;
import com.nathaniel.travel_guide_app.entity.PackingChecklistItem;
import lombok.Data;

@Data
public class PackingCategoryDTO {
    private String category;
    private List<PackingChecklistItem> items;
}
