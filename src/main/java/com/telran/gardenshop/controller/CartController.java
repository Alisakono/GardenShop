package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.CartItemDto;
import com.telran.gardenshop.dto.ItemRequest;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.repository.CartRepository;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
@Validated
@Slf4j
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;


    @Autowired
    public CartController(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;

    }

    @GetMapping
    public ResponseEntity<CartDto> getCart(@RequestParam String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUsersByEmail(email));
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            CartDto cartDto = cartService.getCartByEmail(email);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<CartDto> addProductToCart(@RequestBody ItemRequest itemRequest, @RequestParam String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUsersByEmail(email));
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            CartDto cartDto = cartService.addProductToCart(itemRequest, email);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);


        }
    }
}