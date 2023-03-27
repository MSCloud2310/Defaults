package com.defaults.marketplace.msorders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.defaults.marketplace.msorders.models.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {

	Cart findByUserId(Integer userId);
}
