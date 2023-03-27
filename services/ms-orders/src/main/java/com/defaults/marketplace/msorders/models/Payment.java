package com.defaults.marketplace.msorders.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Payment {
	@Id
	private String id;
	private String confirmationCode;
	private Date date;
	private PaymentMethod paymentMethod;
	private String cardNumber;
	private String cardCvv;
	private String cardExpiryDate;

	public Payment(String id, String confirmationCode, Date date, PaymentMethod paymentMethod, String cardNumber,
			String cardCvv, String cardExpiryDate) {
		this.id = id;
		this.confirmationCode = confirmationCode;
		this.date = date;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
