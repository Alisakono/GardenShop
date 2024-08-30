package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper orderMapper;

@Autowired
    public OrderService(OrderRepository repository, OrderMapper orderMapper) {
        this.repository = repository;
        this.orderMapper = orderMapper;
    }

    public OrderResponseDto getOrderById(Long id) {
        Optional<Order> optionalOrder = repository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return orderMapper.toResponseDto(order);
        } else {
            return null;
        }
    }

    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto) {
       Order order = orderMapper.dtoToEntity(orderRequestDto);
       Order savedOrder = repository.save(order);
       return orderMapper.entityToDto(savedOrder);
    }

}
