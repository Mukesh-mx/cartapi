package com.example.cartapi.controller;

import com.example.cartapi.model.Cart;
import com.example.cartapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<Cart> addItem(@PathVariable Long userId, @RequestBody Cart cart) {
        Cart createdCart = cartService.addItem(userId, cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }


    @GetMapping("/{userId}")
    public List<Cart> viewCart(@PathVariable Long userId) {
        // Check if the user exists in the system (you could call a service method to check)
        if (!cartService.userExists(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        return cartService.getCartByUser(userId);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // This will return a 404 status
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @PutMapping("/update/{itemId}")
    public Cart updateItem(@PathVariable Long itemId, @RequestBody Cart cart) {
        return cartService.updateItem(itemId, cart);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
    }
}