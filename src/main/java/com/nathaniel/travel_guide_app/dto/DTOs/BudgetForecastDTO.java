package com.nathaniel.travel_guide_app.dto.DTOs;

import java.math.BigDecimal;
import java.util.Map;
import com.nathaniel.travel_guide_app.enums.BudgetCategory;

public record BudgetForecastDTO(
    BigDecimal totalBudget,
    int numberOfDays,
    String travelStyle,
    BigDecimal dailyBudget,
    Map<BudgetCategory, BigDecimal> allocatedAmounts,  // how much per category
    Map<BudgetCategory, BigDecimal> allocatedPercentages, // percentage breakdown
    Map<BudgetCategory, String> tips,                   // "Aim for €80/night hotels"
    BudgetSummaryDTO currentStatus                      // real-time tracking overlay
) {}