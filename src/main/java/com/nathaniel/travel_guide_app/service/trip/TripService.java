package com.nathaniel.travel_guide_app.service.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.nathaniel.travel_guide_app.dto.DTOs.BudgetForecastDTO;
import com.nathaniel.travel_guide_app.dto.DTOs.BudgetSummaryDTO;
import com.nathaniel.travel_guide_app.dto.request.trip.TripRequest;
import com.nathaniel.travel_guide_app.dto.response.trip.TripDayResponse;
import com.nathaniel.travel_guide_app.dto.response.trip.TripResponse;
import com.nathaniel.travel_guide_app.entity.Country;
import com.nathaniel.travel_guide_app.entity.Place;
import com.nathaniel.travel_guide_app.entity.Trip;
import com.nathaniel.travel_guide_app.entity.TripActivity;
import com.nathaniel.travel_guide_app.entity.TripDay;
import com.nathaniel.travel_guide_app.enums.BudgetCategory;
import com.nathaniel.travel_guide_app.enums.TravelStyle;
import com.nathaniel.travel_guide_app.enums.TripStatus;
import com.nathaniel.travel_guide_app.mapper.TripDayMapper;
import com.nathaniel.travel_guide_app.mapper.TripMapper;
import com.nathaniel.travel_guide_app.repository.CountryRepository;
import com.nathaniel.travel_guide_app.repository.Trip.PlaceRepository;
import com.nathaniel.travel_guide_app.repository.Trip.TripActivityRepository;
import com.nathaniel.travel_guide_app.repository.Trip.TripDayRepository;
import com.nathaniel.travel_guide_app.repository.Trip.TripRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TripActivityRepository tripActivityRepository;
    private final TripDayRepository tripDayRepository;
    private final PlaceRepository placeRepository;
    private final CountryRepository countryRepository;
    private final TripMapper tripMapper;
    private final TripDayMapper tripDayMapper;

    private final BudgetTrackingService budgetTrackingService;
    private final BudgetAllocationService budgetAllocationService;

    public List<Trip> getByUser(Long userId) {
        return tripRepository.findByUserId(userId);
    }

    public List<Trip> getByUserAndStatus(Long userId, TripStatus status) {
        return tripRepository.findByUserIdAndStatus(userId, status);
    }

    public Trip getById(Long id) {
        return tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
    }

    @Transactional
    public TripResponse createTrip(TripRequest tripRequest) {
        Country country = countryRepository.findById(tripRequest.getCountryId())
            .orElseThrow(() -> new RuntimeException(
                "Country not found with id: " + tripRequest.getCountryId()
            ));

        Trip trip = tripMapper.toEntity(tripRequest, country);
        Trip saved = tripRepository.save(trip);

        return tripMapper.toResponse(saved);
    }

    @Transactional
    public TripResponse generateTrip(TripRequest tripRequest) {
        if (tripRequest.getNumberOfDays() == null || tripRequest.getNumberOfDays() <= 0) {
            throw new RuntimeException("Number of days must be greater than 0");
        }

        Long countryId = tripRequest.getCountryId();

        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new RuntimeException("Country not found with id: " + countryId));

        if (tripRequest.getStartDate() == null) {
            tripRequest.setStartDate(LocalDate.now());
        }

        tripRequest.setEndDate(
            tripRequest.getStartDate().plusDays(tripRequest.getNumberOfDays() - 1)
        );

        if (tripRequest.getTitle() == null || tripRequest.getTitle().isBlank()) {
            tripRequest.setTitle(country.getName() + " " + tripRequest.getNumberOfDays() + "-Day Trip");
        }

        if (tripRequest.getUserId() == null) {
            tripRequest.setUserId(1L); // temporary until auth is ready
        }

        Trip trip = tripMapper.toEntity(tripRequest, country);
        trip.setStatus(TripStatus.DRAFT);

        Trip saved = tripRepository.save(trip);

        generateTripDays(saved);

        return tripMapper.toResponse(saved);
    }

    @Transactional
    public TripResponse updateTrip(Long id, TripRequest tripRequest) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));

        Long countryId = tripRequest.getCountryId() != null ? tripRequest.getCountryId() : trip.getCountry().getId();

        Country country = countryRepository.findById(countryId)
            .orElseThrow(() -> new RuntimeException("Country not found with id: " + countryId));

        tripMapper.updateEntity(trip, tripRequest, country);

        Trip saved = tripRepository.save(trip);
        return tripMapper.toResponse(saved);
    }

    @Transactional
    public void deleteTrip(Long id) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));

        tripRepository.delete(trip);
    }

    @Transactional
    public TripResponse updateStatus(Long id, TripStatus status) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));

        trip.setStatus(status);

        Trip saved = tripRepository.save(trip);
        return tripMapper.toResponse(saved);
    }

    private void generateTripDays(Trip trip) {
        for (int i = 0; i < trip.getNumberOfDays(); i++) {
            TripDay day = new TripDay();

            day.setTrip(trip);
            day.setDayNumber(i + 1);
            day.setDate(trip.getStartDate().plusDays(i));

            TripDay savedDay = tripDayRepository.save(day);

            generateActivities(savedDay, trip);
        }
    }

    private void generateActivities(TripDay day, Trip trip) {
        List<Place> places = placeRepository.findByCountryId(trip.getCountry().getId());

        if (places.isEmpty()) {
            throw new RuntimeException(
                "No published places found for: " + trip.getCountry().getName() +
                ". Add and publish places via the admin panel first."
            );
        }

        List<Place> shuffled = new ArrayList<>(places);
        Collections.shuffle(shuffled);

        String[][] slots = pickSlots(trip.getTravelStyle());

        for (int i = 0; i < slots.length && i < places.size(); i++) {
            String[] time = slots[i];

            TripActivity activity = new TripActivity();
            activity.setTripDay(day);
            activity.setPlace(places.get(i));
            activity.setTitle("Visit " + places.get(i).getName()); 
            activity.setStartTime(LocalTime.parse(time[0]));
            activity.setEndTime(LocalTime.parse(time[1]));
            activity.setSortOrder(i + 1);
            activity.setNotes("Visit " + places.get(i).getName());

            tripActivityRepository.save(activity);
        }
    }

    public BudgetSummaryDTO getBudgetSummary(Long tripId) {
        return budgetTrackingService.getBudgetSummary(tripId);
    }

    public BudgetForecastDTO getBudgetForecast(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + tripId));

        BigDecimal totalBudget = trip.getTotalBudget();
        
        if (totalBudget == null || totalBudget.compareTo(BigDecimal.ZERO) <= 0) {
            return new BudgetForecastDTO(
                BigDecimal.ZERO,
                trip.getNumberOfDays(),
                trip.getTravelStyle() != null ? trip.getTravelStyle().name() : "SOLO",
                BigDecimal.ZERO,
                Collections.emptyMap(),
                Collections.emptyMap(),
                Collections.emptyMap(),
                budgetTrackingService.getBudgetSummary(tripId)
            );
        }

        Map<BudgetCategory, BigDecimal> percentages = 
            budgetAllocationService.getAllocationPercentages(trip.getTravelStyle());

        Map<BudgetCategory, BigDecimal> amounts = new LinkedHashMap<>();
        Map<BudgetCategory, String> tips = new LinkedHashMap<>();

        int days = trip.getNumberOfDays();

        percentages.forEach((category, pct) -> {
            BigDecimal amount = totalBudget.multiply(pct).setScale(2, RoundingMode.HALF_UP);
            amounts.put(category, amount);
            tips.put(category, generateTip(category, amount, days));
        });

        BudgetSummaryDTO currentStatus = budgetTrackingService.getBudgetSummary(tripId);

        return new BudgetForecastDTO(
            totalBudget,
            days,
            trip.getTravelStyle() != null ? trip.getTravelStyle().name() : "SOLO",
            totalBudget.divide(BigDecimal.valueOf(Math.max(days, 1)), 2, RoundingMode.HALF_UP),
            amounts,
            percentages,
            tips,
            currentStatus
        );
    }

    private String generateTip(BudgetCategory category, BigDecimal amount, int days) {
        BigDecimal perDay = amount.divide(BigDecimal.valueOf(days), 0, RoundingMode.HALF_UP);
        
        return switch (category) {
            case ACCOMMODATION -> "Target hotels under €" + perDay + "/night";
            case FOOD_DINING -> "Budget ~€" + perDay + "/day for meals";
            case ACTIVITIES_ATTRACTIONS -> "Allocate ~€" + perDay + "/day for paid attractions";
            case TRANSPORTATION -> "Set aside €" + perDay + "/day for local transport";
            case EMERGENCY_BUFFER -> "Keep €" + amount + " as safety net";
            default -> "Plan ~€" + perDay + "/day for this category";
        };
    }

    public TripResponse getResponseById(Long id) {
        Trip trip = tripRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));

        return tripMapper.toResponse(trip);
    }

    public List<TripDayResponse> getResponsesByTripId(Long tripId) {
        return tripDayRepository.findByTripIdOrderByDayNumberAsc(tripId)
            .stream()
            .map(tripDayMapper::toResponse)
            .toList();
    }

    public List<TripResponse> getResponsesByUser(Long userId) {
        return tripRepository.findByUserId(userId)
            .stream()
            .map(tripMapper::toResponse)
            .toList();
    }

    public List<TripResponse> getResponsesByUserAndStatus(Long userId, TripStatus status) {
        return tripRepository.findByUserIdAndStatus(userId, status)
            .stream()
            .map(tripMapper::toResponse)
            .toList();
    }

    private String[][] pickSlots(TravelStyle travelStyle) {
        if (travelStyle == null) {
            travelStyle = TravelStyle.SOLO;
        }

        return switch (travelStyle) {
            case BACKPACKING -> new String[][] {
                {"07:00", "09:00"},
                {"09:30", "11:30"},
                {"13:00", "15:00"},
                {"15:30", "18:00"}
            };
            case BUSINESS -> new String[][] {
                {"08:00", "10:00"},
                {"12:00", "13:00"},
                {"18:00", "20:00"}
            };
            case COUPLE -> new String[][] {
                {"09:00", "11:00"},
                {"13:00", "15:00"},
                {"17:00", "19:00"}
            };
            case FAMILY -> new String[][] {
                {"09:00", "11:00"},
                {"13:00", "15:00"},
                {"16:00", "18:00"}
            };
            case FRIENDS -> new String[][] {
                {"10:00", "12:00"},
                {"13:30", "15:30"},
                {"17:00", "19:00"}
            };
            case SOLO -> new String[][] {
                {"08:00", "10:00"},
                {"10:30", "12:00"},
                {"13:30", "15:00"},
                {"15:30", "17:00"}
            };
        };
    }
}