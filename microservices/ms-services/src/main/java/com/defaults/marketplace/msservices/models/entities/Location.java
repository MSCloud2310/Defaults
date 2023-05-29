package com.defaults.marketplace.msservices.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", unique = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "capital")
    private String capital;

    @Column(name = "currencies")
    private String currencies;

    @Column(name = "languages")
    private String languages;

    @Column(name = "region")
    private String region;

    @Column(name = "population")
    private String population;

    @Column(name = "flag")
    private String flag;
    
    @Column(name = "service_id")
    private Integer serviceId;
}
