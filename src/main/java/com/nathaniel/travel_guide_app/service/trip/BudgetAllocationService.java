package com.nathaniel.travel_guide_app.service.trip;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.nathaniel.travel_guide_app.enums.BudgetCategory;
import com.nathaniel.travel_guide_app.enums.TravelStyle;

@Component
public class BudgetAllocationService {
    /**
     * Returns recommended percentage allocation per category
     * based on travel style.
     * 
     * These ratios come from travel industry data:
     * - Backpackers spend more on experiences, less on accommodation
     * - Families spend more on food and comfort
     * - Business travelers prioritize convenience
     */
    public Map<BudgetCategory, BigDecimal> getAllocationPercentages(TravelStyle style) {
        if (style == null) style = TravelStyle.SOLO;
        
        Map<BudgetCategory, BigDecimal> allocations = new LinkedHashMap<>();
        
        switch (style) {
            case BACKPACKING -> {
                allocations.put(BudgetCategory.ACCOMMODATION,        new BigDecimal("0.20")); // hostels
                allocations.put(BudgetCategory.FOOD_DINING,          new BigDecimal("0.25")); // street food + groceries
                allocations.put(BudgetCategory.ACTIVITIES_ATTRACTIONS, new BigDecimal("0.25")); // experiences focus
                allocations.put(BudgetCategory.TRANSPORTATION,       new BigDecimal("0.15"));
                allocations.put(BudgetCategory.SHOPPING,             new BigDecimal("0.05"));
                allocations.put(BudgetCategory.MISCELLANEOUS,        new BigDecimal("0.05"));
                allocations.put(BudgetCategory.EMERGENCY_BUFFER,     new BigDecimal("0.05"));
            }
            case FAMILY -> {
                allocations.put(BudgetCategory.ACCOMMODATION,        new BigDecimal("0.30")); // bigger rooms
                allocations.put(BudgetCategory.FOOD_DINING,          new BigDecimal("0.25")); // restaurants
                allocations.put(BudgetCategory.ACTIVITIES_ATTRACTIONS, new BigDecimal("0.20"));
                allocations.put(BudgetCategory.TRANSPORTATION,       new BigDecimal("0.10"));
                allocations.put(BudgetCategory.SHOPPING,             new BigDecimal("0.05"));
                allocations.put(BudgetCategory.MISCELLANEOUS,        new BigDecimal("0.05"));
                allocations.put(BudgetCategory.EMERGENCY_BUFFER,     new BigDecimal("0.05"));
            }
            case BUSINESS -> {
                allocations.put(BudgetCategory.ACCOMMODATION,        new BigDecimal("0.35"));
                allocations.put(BudgetCategory.FOOD_DINING,          new BigDecimal("0.30"));
                allocations.put(BudgetCategory.ACTIVITIES_ATTRACTIONS, new BigDecimal("0.15"));
                allocations.put(BudgetCategory.TRANSPORTATION,       new BigDecimal("0.10"));
                allocations.put(BudgetCategory.SHOPPING,             new BigDecimal("0.05"));
                allocations.put(BudgetCategory.MISCELLANEOUS,        new BigDecimal("0.03"));
                allocations.put(BudgetCategory.EMERGENCY_BUFFER,     new BigDecimal("0.02"));
            }
            case COUPLE -> {
                allocations.put(BudgetCategory.ACCOMMODATION,        new BigDecimal("0.28"));
                allocations.put(BudgetCategory.FOOD_DINING,          new BigDecimal("0.28")); // romantic dinners
                allocations.put(BudgetCategory.ACTIVITIES_ATTRACTIONS, new BigDecimal("0.18"));
                allocations.put(BudgetCategory.TRANSPORTATION,       new BigDecimal("0.12"));
                allocations.put(BudgetCategory.SHOPPING,             new BigDecimal("0.06"));
                allocations.put(BudgetCategory.MISCELLANEOUS,        new BigDecimal("0.04"));
                allocations.put(BudgetCategory.EMERGENCY_BUFFER,     new BigDecimal("0.04"));
            }
            default -> { // SOLO, FRIENDS
                allocations.put(BudgetCategory.ACCOMMODATION,        new BigDecimal("0.25"));
                allocations.put(BudgetCategory.FOOD_DINING,          new BigDecimal("0.25"));
                allocations.put(BudgetCategory.ACTIVITIES_ATTRACTIONS, new BigDecimal("0.22"));
                allocations.put(BudgetCategory.TRANSPORTATION,       new BigDecimal("0.13"));
                allocations.put(BudgetCategory.SHOPPING,             new BigDecimal("0.05"));
                allocations.put(BudgetCategory.MISCELLANEOUS,        new BigDecimal("0.05"));
                allocations.put(BudgetCategory.EMERGENCY_BUFFER,     new BigDecimal("0.05"));
            }
        }
        
        return allocations;
    }
}
