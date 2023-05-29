package com.defaults.marketplace.msorders.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.defaults.marketplace.msorders.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defaults.marketplace.msorders.exceptions.EmptyCartException;
import com.defaults.marketplace.msorders.exceptions.NotFoundException;
import com.defaults.marketplace.msorders.repositories.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(String id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new NotFoundException("Payment not found");
        }
        return payment;
    }

    public List<Payment> getPaymentsByUserId(Integer userId) {
        return paymentRepository.findByUserId(userId);
    }

    public Order payCart(Integer userId, Payment payment) {
        Cart cart = cartService.getCartByUserId(userId);
        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new EmptyCartException("Cart is empty and can't be paid");
        }
        if (payment.getPaymentMethod() == null) {
            throw new IllegalArgumentException("Payment method is required");
        }
        if (payment.getCardNumber() == null) {
            throw new IllegalArgumentException("Card number is required");
        }
        if (payment.getCardCvv() == null) {
            throw new IllegalArgumentException("Card CVV is required");
        }
        if (payment.getCardExpiryDate() == null) {
            throw new IllegalArgumentException("Card expiry date is required");
        }

        Payment newPayment = new Payment(
                userId,
                LocalDateTime.now(),
                payment.getPaymentMethod(),
                payment.getCardNumber(),
                payment.getCardCvv(),
                payment.getCardExpiryDate());
        newPayment = paymentRepository.save(newPayment);

        try {
            cart.setItems(Weather.addWeather(cart));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Order order = orderService.createOrder(newPayment.getId(), new PaymentDetails(
                payment.getPaymentMethod(),
                payment.getCardNumber().substring(payment.getCardNumber().length() - 4)), cart);

        cartService.emptyCart(userId);

        return order;
    }




    public Payment updatePayment(String id, Payment payment) {
        Payment existingPayment = paymentRepository.findById(id).orElse(null);
        if (existingPayment == null) {
            throw new NotFoundException("Payment not found");
        }
        existingPayment.setDateCreated(
                payment.getDateCreated() != null ? payment.getDateCreated() : existingPayment.getDateCreated());
        existingPayment.setPaymentMethod(
                payment.getPaymentMethod() != null ? payment.getPaymentMethod() : existingPayment.getPaymentMethod());
        existingPayment.setCardNumber(
                payment.getCardNumber() != null ? payment.getCardNumber() : existingPayment.getCardNumber());
        existingPayment.setCardCvv(
                payment.getCardCvv() != null ? payment.getCardCvv() : existingPayment.getCardCvv());
        existingPayment.setCardExpiryDate(
                payment.getCardExpiryDate() != null ? payment.getCardExpiryDate()
                        : existingPayment.getCardExpiryDate());
        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(String id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) {
            throw new NotFoundException("Payment not found");
        }
        paymentRepository.delete(payment);
    }
}
