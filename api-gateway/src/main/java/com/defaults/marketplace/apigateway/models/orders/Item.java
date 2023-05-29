package com.defaults.marketplace.apigateway.models.orders;

import java.time.LocalDateTime;
import java.util.List;

public class Item {

    private LocalDateTime dateAdded;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Integer quantity = 0;
    private Double cost = 0.0;
    private Integer serviceId;
    private ServiceDetails serviceDetails;
    private List<Weather> weathers;

    public Item(LocalDateTime dateAdded, LocalDateTime dateFrom, LocalDateTime dateTo, Integer quantity, Double cost,
            Integer serviceId, ServiceDetails serviceDetails, List<Weather> weathers) {
        this.dateAdded = dateAdded;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.quantity = quantity;
        this.cost = cost;
        this.serviceId = serviceId;
        this.serviceDetails = serviceDetails;
        this.weathers = weathers;
    }

    public Item() {
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
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

    public ServiceDetails getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(ServiceDetails serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
