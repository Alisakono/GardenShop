package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;

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
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequestDto order) {
        OrderRequestDto createdOrder = orderService.createOrder(new Order());

        return new ResponseEntity<>(createdOrder,HttpStatus.CREATED);
    }
    }


