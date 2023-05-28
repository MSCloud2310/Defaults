package com.defaults.marketplace.productsearch.models.entities;

import com.defaults.marketplace.productsearch.models.enumerations.ServiceCategory;
import com.defaults.marketplace.productsearch.models.enumerations.ServiceMealType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "services")
public class ServiceC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", unique = true)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ServiceCategory category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "capacity")
    private Integer capacity;

    // ServiceTransportation and ServiceTour

    @Column(name = "origin")
    private String origin;

    @Column(name = "destination")
    private String destination;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "departure_time")
    private LocalDate departureTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "arrival_time")
    private LocalDate arrivalTime;

    @Column(name = "transportation_type")
    private String transportationType;

    // ServiceLodging

    @Column(name = "location")
    private String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "checkin_time")
    private LocalDate checkinTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "checkout_time")
    private LocalDate checkoutTime;

    @Column(name = "breakfast")
    private Boolean breakfast;

    @Column(name = "internet")
    private Boolean internet;

    //ServiceMeal

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type")
    private ServiceMealType mealType;

    @Column(name = "servings")
    private Integer servings;

    @Column(name = "vegan")
    private Boolean vegan;

    /* Relación con otras entidades*/
    @Column(name = "provider_id")
    private Integer providerId;

    @OneToMany
    @JoinColumn(name = "service_id")
    private List<Question> questions;
}
