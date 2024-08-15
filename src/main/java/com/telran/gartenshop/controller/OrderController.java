package com.telran.gartenshop.controller;

import com.telran.gartenshop.entity.OrderInfo;
import com.telran.gartenshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }
    @GetMapping
    public List<OrderInfo> getAllOrders() {
        return service.getAllOrders();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderInfo> getOrderById(@PathVariable Long id) {
        Optional<OrderInfo> order = service.getOrderById(id);
        return order.map(value-> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<OrderInfo> createOrder(@RequestBody OrderInfo orderInfo) {
        OrderInfo createdOrderInfo = service.createOrder(orderInfo);
        return new ResponseEntity<>(createdOrderInfo, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderInfo> updateOrder(@PathVariable Long id, @RequestBody OrderInfo orderInfo) {
        if (!service.getOrderById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderInfo.setId(id);
        OrderInfo updatedOrderInfo = service.updateOrder(orderInfo);
        return new ResponseEntity<>(updatedOrderInfo, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (service.deleteOrder(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
