package com.telran.controller;


import com.telran.entity.Cart;
import com.telran.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public ResponseEntity<Cart> getCartByUser(@RequestParam String username) {
        Optional<Cart> cart = service.getCartByUser(username);
        return cart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        service.add(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        boolean isUpdated = service.updateCart(cart);
        return new ResponseEntity<>(cart, isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

