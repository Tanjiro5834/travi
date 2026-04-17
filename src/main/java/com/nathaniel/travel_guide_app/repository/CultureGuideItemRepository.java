package com.nathaniel.travel_guide_app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nathaniel.travel_guide_app.entity.CultureGuideItem;

public interface CultureGuideItemRepository extends JpaRepository<CultureGuideItem, Long> {
    List<CultureGuideItem> findByCountryIdOrderByTypeAscIdAsc(Long countryId);
    List<CultureGuideItem> findByCountryIdAndTypeOrderByIdAsc(Long countryId, String type);
}