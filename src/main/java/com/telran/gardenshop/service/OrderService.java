package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.enums.Status;
import com.telran.gardenshop.exceptionhandler.OrderNotFoundException;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderItemRepository;
import com.telran.gardenshop.repository.OrderRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ProductRepository repository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, ProductRepository repository, OrderItemRepository orderItemRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.repository = repository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.toOrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDto createOrder(@Valid @NotNull OrderRequestDto orderRequestDto, String email) throws IllegalArgumentException {
        User user = userRepository.findUsersByEmail(email);
        log.info("User found: {}", user);
        Order order = orderMapper.dtoToEntity(orderRequestDto);
        order.setUser(user);
        List<OrderItem> items = order.getItems();
        for (OrderItem item : items) {
            item.setOrder(order);
            item.setPriceAtPurchase(BigDecimal.ZERO);
            item.setProduct(repository.getReferenceById(item.getProduct().getId()));
        }
        Order save = orderRepository.save(order);
        return orderMapper.toOrderResponseDto(save);
    }

    @Transactional
    @Scheduled(fixedRate = 30_000)
    public void updateOrderStatus() {
        log.info("Updating order statuses...");
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            Status currentStatus = order.getStatus();
            Status newStatus = switch (currentStatus) {
                case AWAITING_PAYMENT -> Status.PENDING;
                case PENDING -> Status.SHIPPED;
                case SHIPPED -> Status.DELIVERED;
                default -> currentStatus;
            };
            if (newStatus != currentStatus) {
                order.setStatus(newStatus);
                orderRepository.save(order);
                log.info("Order ID {} status updated to {}", order.getId(), newStatus);
            }
        }
        log.info("Order status update complete.");
    }
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        if (order.getStatus() == Status.AWAITING_PAYMENT || order.getStatus() == Status.PENDING) {
            order.setStatus(Status.CANCELLED);
            orderRepository.save(order);
        }
    }
    public Status getOrderStatusById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return order.getStatus();
    }
}




