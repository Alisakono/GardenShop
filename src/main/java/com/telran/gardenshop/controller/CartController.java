package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.CartItemDto;
import com.telran.gardenshop.dto.ItemRequest;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.repository.CartRepository;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.security.AuthService;
import com.telran.gardenshop.service.CartService;
import com.telran.gardenshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final UserService userService;
    private final AuthService authService;


    @Autowired
    public CartController(CartService cartService, UserRepository userRepository, UserService userService, AuthService authService) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get cart")
    public ResponseEntity<CartDto> getCart() {
        Optional<User> user = userRepository.findUsersByEmail(authService.getAuthInfo().getLogin());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            CartDto cartDto = cartService.getCartByEmail(authService.getAuthInfo().getLogin());
            return new ResponseEntity<>(cartDto, HttpStatus.OK);
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Operation(summary = "Add product to cart")
    public ResponseEntity<CartDto> addProductToCart(@RequestBody ItemRequest itemRequest) {
        Optional<User> user = userRepository.findUsersByEmail(authService.getAuthInfo().getLogin());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            CartDto cartDto = cartService.addProductToCart(itemRequest);
            return new ResponseEntity<>(cartDto, HttpStatus.OK);


        }
    }
}