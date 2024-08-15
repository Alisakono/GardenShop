package com.telran.gartenshop.controller;


import com.telran.gartenshop.entity.Cart;
import com.telran.gartenshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public ResponseEntity<Cart> getCartByUser(@RequestParam String user) {
        Cart cart = service.getCartByUser(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        service.add(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
        boolean isUpdated = service.updateCart(cart.getUser(), cart);
        return new ResponseEntity<>(cart, isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<Cart> deleteByUser(@PathVariable String user) {
        service.deleteByUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

