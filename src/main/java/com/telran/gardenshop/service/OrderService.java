package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderRepository;
import com.telran.gardenshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository repository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository repository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.repository = repository;
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


    public OrderRequestDto createOrder(Order order) {
        order = new Order();
        order.setId(order.getId());
        Order save = orderRepository.save(order);
        return new OrderRequestDto();
    }
}
