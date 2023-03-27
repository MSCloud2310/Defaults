package com.defaults.marketplace.msorders.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {

	@Id
	private String id;
	private Integer userId;
	private LocalDateTime dateCreated;
	private Double totalCost = 0.0;
	private List<Item> items;
	private PaymentDetails paymentDetails;

	public Order(String id, Integer userId, LocalDateTime dateCreated, Double totalCost, List<Item> items,
			PaymentDetails paymentDetails) {
		this.id = id;
		this.userId = userId;
		this.dateCreated = dateCreated;
		this.totalCost = totalCost;
		this.items = items;
		this.paymentDetails = paymentDetails;
	}

	public Order() {
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

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
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

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public void calculateTotalCost() {
		Double totalCost = 0.0;
		for (Item item : items) {
			totalCost += item.getCost();
		}
		this.totalCost = totalCost;
	}
}
