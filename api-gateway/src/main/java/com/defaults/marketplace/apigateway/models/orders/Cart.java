package com.defaults.marketplace.apigateway.models.orders;

import java.util.List;

public class Cart {

    private String id;
    private Integer userId;
    private Double totalCost = 0.0;
    private List<Item> items;

    public Cart(Integer userId, Double totalCost, List<Item> items) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.items = items;
    }

    public Cart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void calculateTotalCost() {
        Double totalCost = 0.0;
        for (Item item : items) {
            totalCost += item.getCost();
        }
        this.totalCost = totalCost;
    }
}