package com.defaults.marketplace.msorders.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.defaults.marketplace.msorders.exceptions.AlreadyExistsException;
import com.defaults.marketplace.msorders.exceptions.NotFoundException;
import com.defaults.marketplace.msorders.models.Cart;
import com.defaults.marketplace.msorders.models.Item;
import com.defaults.marketplace.msorders.repositories.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartByUserId(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        return cart;
    }

    public Cart createCartByUserId(Integer userId) {
        if (cartRepository.findByUserId(userId) != null) {
            throw new AlreadyExistsException("Cart already exists");
        }
        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartRepository.save(cart);
    }

    public Cart updateCartByUserId(Integer userId, Cart cart) {
        Cart existingCart = cartRepository.findByUserId(userId);
        if (existingCart == null) {
            throw new NotFoundException("Cart not found");
        }
        existingCart.setItems(cart.getItems() != null ? cart.getItems() : existingCart.getItems());
        existingCart.calculateTotalCost();
        return cartRepository.save(cart);
    }

    public List<Item> getItemsFromCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        return cart.getItems() != null ? cart.getItems() : new ArrayList<Item>();
    }

    public Cart addItemToCart(Integer userId, Item item) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<Item>());
        }

        item.setDateAdded(LocalDateTime.now());
        cart.getItems().add(item);
        cart.calculateTotalCost();
        return cartRepository.save(cart);
    }

    public Item getItemFromCart(Integer userId, int position) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        if (position < 0 || cart.getItems().size() <= position) {
            throw new NotFoundException("Item not found");
        }
        return cart.getItems().get(position);
    }

    public Cart updateItemFromCart(Integer userId, int position, Item item) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        if (position < 0 || cart.getItems().size() <= position) {
            throw new NotFoundException("Item not found");
        }
        cart.getItems().set(position, item);
        cart.calculateTotalCost();
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Integer userId, int position) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        if (position < 0 || cart.getItems().size() <= position) {
            throw new NotFoundException("Item not found");
        }
        cart.getItems().remove(position);
        cart.calculateTotalCost();
        return cartRepository.save(cart);
    }

    public Cart emptyCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        cart.getItems().clear();
        cart.calculateTotalCost();
        return cartRepository.save(cart);
    }

    public void deleteCartByUserId(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new NotFoundException("Cart not found");
        }
        cartRepository.delete(cart);
    }
}
