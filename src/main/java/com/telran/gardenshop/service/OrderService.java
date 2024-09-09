package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderRepository;
import com.telran.gardenshop.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
   private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper,UserRepository userRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

    }

    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return orderMapper.toResponseDto(order);
        } else {
            return null;
        }
    }


    public Order createOrder(@NotNull OrderRequestDto orderRequestDto, User user) {
        if (orderRequestDto.getDeliveryAddress() ==null || orderRequestDto.getDeliveryMethod() ==null) {
            throw new IllegalArgumentException("Delivery address and delivery method are required.");
        }
            Order order = new Order();
        order.setDeliveryAddress(orderRequestDto.getDeliveryAddress());
        order.setDeliveryMethod(orderRequestDto.getDeliveryMethod());
        order.setStatus("PENDING");
       orderRepository.save(new Order());
        return order;
    }
    public void addOrderToUser(Long orderId, String email) {
        User user = userRepository.findUsersByEmail(email);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setUsers(user);
        orderRepository.save(order);

}}
