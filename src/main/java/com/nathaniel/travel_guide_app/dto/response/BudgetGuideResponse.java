package com.nathaniel.travel_guide_app.dto.response;

import java.math.BigDecimal;

public class BudgetGuideResponse {
    private BigDecimal budgetDaily;
    private BigDecimal midRangeDaily;
    private BigDecimal luxuryDaily;
    private BigDecimal averageHotelCost;
    private BigDecimal averageMealCost;
    private BigDecimal averageTransportCost;
    private String currency;
}
