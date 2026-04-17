package com.nathaniel.travel_guide_app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nathaniel.travel_guide_app.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {     
     Optional<Country> findBySlug(String slug);    
     Optional<Country> findFirstByOrderByViewCountDesc();
     List<Country> findByNameContainingIgnoreCase(String keyword); 
}