package com.defaults.marketplace.msorders.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.defaults.marketplace.msorders.models.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	Payment findByConfirmationCode(String confirmationCode);

	List<Payment> findByUserId(Integer userId);
}
