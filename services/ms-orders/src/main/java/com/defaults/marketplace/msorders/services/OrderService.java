package com.defaults.marketplace.msorders.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defaults.marketplace.msorders.exceptions.NotFoundException;
import com.defaults.marketplace.msorders.models.Cart;
import com.defaults.marketplace.msorders.models.Order;
import com.defaults.marketplace.msorders.models.PaymentDetails;
import com.defaults.marketplace.msorders.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(String id) {
		Order order = orderRepository.findById(id).orElse(null);
		if (order == null) {
			throw new NotFoundException("Order not found");
		}
		return order;
	}

	public List<Order> getOrdersByUserId(Integer userId) {
		return orderRepository.findByUserId(userId);
	}

	public Order createOrder(String paymentId, PaymentDetails paymentDetails, Cart cart) {
		Order order = new Order(
				paymentId,
				cart.getUserId(),
				LocalDateTime.now(),
				cart.getTotalCost(),
				cart.getItems(),
				paymentDetails);
		return orderRepository.save(order);
	}

	public Order updateOrder(String id, Order order) {
		Order existingOrder = orderRepository.findById(id).orElse(null);
		if (existingOrder == null) {
			throw new NotFoundException("Order not found");
		}
		existingOrder.setDateCreated(
				order.getDateCreated() != null ? order.getDateCreated() : existingOrder.getDateCreated());
		existingOrder.setItems(order.getItems() != null ? order.getItems() : existingOrder.getItems());
		existingOrder.setPaymentDetails(
				order.getPaymentDetails() != null ? order.getPaymentDetails() : existingOrder.getPaymentDetails());
		existingOrder.calculateTotalCost();
		return orderRepository.save(existingOrder);
	}

	public void deleteOrder(String id) {
		Order order = orderRepository.findById(id).orElse(null);
		if (order == null) {
			throw new NotFoundException("Order not found");
		}
		orderRepository.delete(order);
	}
}
