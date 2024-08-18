package com.telran.gardenshop.controller;

import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Get all CartItems by Cart ID
    @GetMapping("/cart/{cartId}")
    public ResponseEntity<Optional<CartItem>> getCartItemsByCartId(@PathVariable Long cartId) {
        Optional<CartItem> cartItems = cartItemService.getCartItemsByCartId(cartId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartItems);
    }

    // Add or update a CartItem
    @PostMapping
    public ResponseEntity<String> addOrUpdateCartItem(@RequestBody CartItem cartItem) {
        cartItemService.addOrUpdateCartItem(cartItem);
        return ResponseEntity.ok("CartItem has been added/updated successfully");
    }

    // Delete a CartItem by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartItemById(@PathVariable Long id) {
        cartItemService.deleteById(id);
        return ResponseEntity.ok("CartItem has been deleted successfully");
    }
}
