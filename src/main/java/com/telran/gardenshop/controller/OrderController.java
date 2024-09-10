package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserRepository userRepository;

    @Autowired
    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;

        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto order = orderService.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto,@RequestParam String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUsersByEmail(email));
        if (userOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      Order order =orderService.createOrder(orderRequestDto,userOptional.get());
       return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}

