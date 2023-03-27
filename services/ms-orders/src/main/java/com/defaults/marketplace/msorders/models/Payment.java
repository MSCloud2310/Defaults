package com.defaults.marketplace.msorders.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Payment {
	@Id
	private String id;
	private String confirmationCode;
	private Integer userId;
	private LocalDateTime datePaid;
	private PaymentMethod paymentMethod;
	private String cardNumber;
	private String cardCvv;
	private String cardExpiryDate;

	public Payment(String confirmationCode, Integer userId, LocalDateTime datePaid, PaymentMethod paymentMethod,
			String cardNumber,
			String cardCvv, String cardExpiryDate) {
		this.confirmationCode = confirmationCode;
		this.userId = userId;
		this.datePaid = datePaid;
		this.paymentMethod = paymentMethod;
		this.cardNumber = cardNumber;
		this.cardCvv = cardCvv;
		this.cardExpiryDate = cardExpiryDate;
	}

	public Payment() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDateTime getDate() {
		return datePaid;
	}

	public void setDate(LocalDateTime datePaid) {
		this.datePaid = datePaid;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}
}
