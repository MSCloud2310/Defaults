package com.defaults.marketplace.msorders.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.defaults.marketplace.msorders.models.Order;
import com.defaults.marketplace.msorders.services.OrderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> findOrders(@RequestParam(name = "userId", required = false) Integer userId) {
		if (userId != null) {
			return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
		}
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable("orderId") String orderId) {
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<Order> updateOrder(@PathVariable("orderId") String orderId, @RequestBody Order order) {
		return ResponseEntity.ok(orderService.updateOrder(orderId, order));
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") String orderId) {
		orderService.deleteOrder(orderId);
		return ResponseEntity.ok().build();
	}
}
