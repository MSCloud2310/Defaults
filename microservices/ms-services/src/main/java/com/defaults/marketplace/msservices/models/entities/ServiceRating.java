package com.defaults.marketplace.msservices.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ratings")
public class ServiceRating {

    @Id
    @GeneratedValue
    Long id;
    private int rating;
    private String comment;
    private int userId;
}
