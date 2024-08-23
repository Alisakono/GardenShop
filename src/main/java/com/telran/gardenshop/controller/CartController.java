package com.telran.gardenshop.controller;
import com.telran.gardenshop.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Validated
@Slf4j
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> addProductToCart(@RequestParam String productId, @RequestParam int quantity) {
        cartService.addProductToCart(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

