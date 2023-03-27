package com.defaults.marketplace.msorders.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.defaults.marketplace.msorders.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

	List<Order> findByUserId(Integer userId);

	@Query("{ 'items.serviceId' : ?0 }")
	List<Order> findByServiceId(Integer serviceId);
}
