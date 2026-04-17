package com.nathaniel.travel_guide_app.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nathaniel.travel_guide_app.entity.BudgetGuide;

public interface BudgetGuideRepository extends JpaRepository<BudgetGuide, Long> {
    Optional<BudgetGuide> findByCountryId(Long countryId);
}