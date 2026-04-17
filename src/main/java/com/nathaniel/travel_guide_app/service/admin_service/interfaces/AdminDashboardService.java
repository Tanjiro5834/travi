package com.nathaniel.travel_guide_app.service.admin_service.interfaces;

import com.nathaniel.travel_guide_app.dto.admin_dto.AdminDashboardResponse;

public interface AdminDashboardService {
    AdminDashboardResponse getDashboardSummary();
}