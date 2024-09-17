package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.exceptionhandler.OrderNotFoundException;
import com.telran.gardenshop.mapper.OrderMapper;
import com.telran.gardenshop.repository.OrderItemRepository;
import com.telran.gardenshop.repository.OrderRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}





