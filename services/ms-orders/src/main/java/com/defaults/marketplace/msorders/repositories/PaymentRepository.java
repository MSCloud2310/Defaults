package com.defaults.marketplace.msorders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.defaults.marketplace.msorders.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	Payment findByConfirmationCode(String confirmationCode);
}
