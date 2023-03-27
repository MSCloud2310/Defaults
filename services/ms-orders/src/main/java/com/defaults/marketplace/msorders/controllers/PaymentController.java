package com.defaults.marketplace.msorders.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.defaults.marketplace.msorders.models.Order;
import com.defaults.marketplace.msorders.models.Payment;
import com.defaults.marketplace.msorders.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public ResponseEntity<List<Payment>> findPayments(@RequestParam(name = "userId", required = false) Integer userId) {
		if (userId != null) {
			return ResponseEntity.ok(paymentService.getPaymentsByUserId(userId));
		}
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@PostMapping
	public ResponseEntity<Order> payCart(@RequestParam("userId") Integer userId, @RequestBody Payment payment) {
		return ResponseEntity.ok(paymentService.payCart(userId, payment));
	}

	@GetMapping("/{paymentId}")
	public ResponseEntity<Payment> findPaymentById(@PathVariable("paymentId") String paymentId) {
		return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
	}

	@PutMapping("/{paymentId}")
	public ResponseEntity<Payment> updatePayment(@PathVariable("paymentId") String paymentId,
			@RequestBody Payment payment) {
		return ResponseEntity.ok(paymentService.updatePayment(paymentId, payment));
	}

	@DeleteMapping("/{paymentId}")
	public ResponseEntity<Void> deletePayment(@PathVariable("paymentId") String paymentId) {
		paymentService.deletePayment(paymentId);
		return ResponseEntity.ok().build();
	}
}
