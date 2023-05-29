package com.defaults.marketplace.apigateway.models.orders;

import java.time.LocalDateTime;

public class Payment {
    private String id;
    private Integer userId;
    private LocalDateTime dateCreated;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardCvv;
    private String cardExpiryDate;

    public Payment(Integer userId, LocalDateTime dateCreated, PaymentMethod paymentMethod,
            String cardNumber,
            String cardCvv, String cardExpiryDate) {
        this.userId = userId;
        this.dateCreated = dateCreated;
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
