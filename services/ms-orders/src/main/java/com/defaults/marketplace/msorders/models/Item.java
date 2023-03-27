package com.defaults.marketplace.msorders.models;

import java.util.Date;

import com.mongodb.lang.Nullable;

public class Item {

	private Date dateAdded;
	private @Nullable Date dateFrom;
	private @Nullable Date dateTo;
	private Integer quantity;
	private Double cost;
	private String serviceId;
	private Integer userId;
	private @Nullable ServiceDetails details;

	public Item(Date dateAdded, Date dateFrom, Date dateTo, Integer quantity, Double cost, String serviceId,
			Integer userId, ServiceDetails details) {
		this.dateAdded = dateAdded;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.quantity = quantity;
		this.cost = cost;
		this.serviceId = serviceId;
		this.userId = userId;
		this.details = details;
	}

	public Item() {
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public @Nullable Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(@Nullable Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public @Nullable Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(@Nullable Date dateTo) {
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

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public @Nullable ServiceDetails getDetails() {
		return details;
	}

	public void setDetails(@Nullable ServiceDetails details) {
		this.details = details;
	}
}
