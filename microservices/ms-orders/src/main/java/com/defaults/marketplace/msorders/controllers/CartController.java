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
import org.springframework.web.bind.annotation.RestController;

import com.defaults.marketplace.msorders.models.Cart;
import com.defaults.marketplace.msorders.models.Item;
import com.defaults.marketplace.msorders.services.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> findAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> findCartByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.createCartByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Integer userId, @RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.updateCartByUserId(userId, cart));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer userId) {
        cartService.deleteCartByUserId(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/items")
    public ResponseEntity<List<Item>> findItemsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.getItemsFromCart(userId));
    }

    @PostMapping("/{userId}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Integer userId, @RequestBody Item item) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, item));
    }

    @DeleteMapping("/{userId}/items")
    public ResponseEntity<Cart> emptyCart(@PathVariable Integer userId) {
        return ResponseEntity.ok(cartService.emptyCart(userId));
    }

    @GetMapping("/{userId}/items/{position}")
    public ResponseEntity<Item> findItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable int position) {
        return ResponseEntity.ok(cartService.getItemFromCart(userId, position));
    }

    @PutMapping("/{userId}/items/{position}")
    public ResponseEntity<Cart> updateItemByUserIdAndPosition(@PathVariable Integer userId, @PathVariable int position,
            @RequestBody Item item) {
        return ResponseEntity.ok(cartService.updateItemFromCart(userId, position, item));
    }

    @DeleteMapping("/{userId}/items/{position}")
    public ResponseEntity<Cart> removeItemByUserIdAndPosition(@PathVariable Integer userId,
            @PathVariable int position) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, position));
    }
}