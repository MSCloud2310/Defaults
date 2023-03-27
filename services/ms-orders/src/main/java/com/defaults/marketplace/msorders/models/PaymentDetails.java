package com.defaults.marketplace.msorders.models;

public class PaymentDetails {

	private PaymentMethod paymentMethod;
	private String cardLastFourDigits;

	public PaymentDetails(PaymentMethod paymentMethod, String cardLastFourDigits) {
		this.paymentMethod = paymentMethod;
		this.cardLastFourDigits = cardLastFourDigits;
	}

	public PaymentDetails() {
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getCardLastFourDigits() {
		return cardLastFourDigits;
	}

	public void setCardLastFourDigits(String cardLastFourDigits) {
		this.cardLastFourDigits = cardLastFourDigits;
	}
}
