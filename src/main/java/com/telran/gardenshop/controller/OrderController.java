package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.enums.Status;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.service.OrderService;
import com.telran.gardenshop.service.OrderStatusService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Table(name = "orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderController(OrderService orderService, UserRepository userRepository, OrderStatusService orderStatusService) {
        this.orderService = orderService;
        this.orderStatusService = orderStatusService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @Operation(summary = "Get order by id")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id) {
        OrderResponseDto responseDto = orderService.getOrderById(id);
        if (responseDto != null) {
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    @Operation(summary = "Get order status by id")
    public ResponseEntity<Status> getOrderStatusById(@PathVariable Long id) {
        Status status = orderStatusService.getOrderStatusById(id);
        return ResponseEntity.ok(status);
    }
    @PostMapping("/{id}/cancel")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    @Operation(summary = "Cancel order status")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id) {
        try {
            orderStatusService.cancelOrder(id);
            return ResponseEntity.ok("Order has been cancelled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/update-statuses")
    @PreAuthorize("hasAnyRole('ROLE_USER','ADMIN')")
    @Operation(summary = "Update order status")
    public ResponseEntity<String> updateOrderStatuses() {
        orderStatusService.updateOrderStatus();
        return ResponseEntity.ok("Order statuses have been updated successfully.");
    }


    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @Operation(summary = "Create order")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderRequestDto order) {
        OrderResponseDto createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);

    }

}
