package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.OrderItemDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;

    }

    @GetMapping("/{id}")

    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto responseDto = orderService.getOrderById(id);
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderRequestDto order, @RequestParam String email) {
        OrderResponseDto createdOrder = orderService.createOrder(order, email);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);

    }
}
