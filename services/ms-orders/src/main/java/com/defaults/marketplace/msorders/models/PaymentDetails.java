package com.defaults.marketplace.msorders.models;

public class PaymentDetails {

	private String confirmationCode;
	private PaymentMethod paymentMethod;
	private String cardLastFourDigits;

	public PaymentDetails(String confirmationCode, PaymentMethod paymentMethod, String cardLastFourDigits) {
		this.confirmationCode = confirmationCode;
		this.paymentMethod = paymentMethod;
		this.cardLastFourDigits = cardLastFourDigits;
	}

	public PaymentDetails() {
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
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
