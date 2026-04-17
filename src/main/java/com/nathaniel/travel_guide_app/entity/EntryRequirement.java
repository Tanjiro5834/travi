package com.nathaniel.travel_guide_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entry_requirements")
public class EntryRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "country_id", nullable = false, unique = true)
    private Country country;

    private Boolean visaRequired;
    private String passportValidityRule;
    private String requiredDocuments;
    private Boolean returnTicketRequired;
    private Boolean proofOfAccommodationRequired;
    private Boolean travelInsuranceRequired;
    private String vaccinationRequirements;

    @Column(length = 2000)
    private String customsNotes;
}