package com.defaults.marketplace.msorders.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {

	@Id
	private String id;
	private Integer userId;
	private Date date;
	private Double totalCost;
	private List<Item> items;
	private PaymentDetails paymentDetails;

	public Order(String id, Integer userId, Date date, Double totalCost, List<Item> items,
			PaymentDetails paymentDetails) {
		this.id = id;
		this.userId = userId;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}
