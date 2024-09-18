package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.dto.OrderStatusDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.enums.Status;
import com.telran.gardenshop.exceptionhandler.OrderNotFoundException;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderItemRepository;
import com.telran.gardenshop.repository.OrderRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import com.telran.gardenshop.security.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final ProductRepository repository;
    private final AuthService authService;


    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository, ProductRepository repository, OrderItemRepository orderItemRepository, AuthService authService, UserService userService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.repository = repository;
        this.authService = authService;

    }

    @Transactional
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.toOrderResponseDto(order);
    }

    @Transactional
    public OrderResponseDto createOrder(@Valid @NotNull OrderRequestDto orderRequestDto) throws IllegalArgumentException {
        if (authService.getAuthInfo().isAuthenticated()) {
            String userEmail = authService.getAuthInfo().getLogin();
            Optional<User> optionalUser = userRepository.findUsersByEmail(userEmail);
            if (optionalUser.isEmpty()) {
                throw new IllegalArgumentException("User not found with email: " + userEmail);
            }
            User user = optionalUser.get();
            Order order = orderMapper.dtoToEntity(orderRequestDto);
            order.setUser(user);
            order.setStatus(Status.AWAITING_PAYMENT);

            List<OrderItem> items = order.getItems();
            for (OrderItem item : items) {
                Product product = repository.getReferenceById(item.getProduct().getId());
                item.setProduct(product);
                BigDecimal priceAtPurchase = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                item.setPriceAtPurchase(priceAtPurchase);
                item.setOrder(order);

            }
            BigDecimal totalPrice = orderMapper.calculateTotalPrice(items);
            log.info("Total price calculated: {}",totalPrice);

            Order save = orderRepository.save(order);
            return orderMapper.toOrderResponseDto(save);
        }

        throw new IllegalArgumentException("User is not authenticated");
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




