package com.defaults.marketplace.msorders.models;

import java.time.LocalDateTime;

import com.mongodb.lang.Nullable;

public class Item {

    private LocalDateTime dateAdded;
    private @Nullable LocalDateTime dateFrom;
    private @Nullable LocalDateTime dateTo;
    private Integer quantity = 0;
    private Double cost = 0.0;
    private Integer serviceId;
    private @Nullable ServiceDetails serviceDetails;

    public Item(LocalDateTime dateAdded, LocalDateTime dateFrom, LocalDateTime dateTo, Integer quantity, Double cost,
            Integer serviceId, ServiceDetails serviceDetails) {
        this.dateAdded = dateAdded;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.quantity = quantity;
        this.cost = cost;
        this.serviceId = serviceId;
        this.serviceDetails = serviceDetails;
    }

    public Item() {
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public @Nullable LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(@Nullable LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public @Nullable LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(@Nullable LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public @Nullable ServiceDetails getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(@Nullable ServiceDetails serviceDetails) {
        this.serviceDetails = serviceDetails;
    }
}
