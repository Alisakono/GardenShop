package com.telran.gardenshop.service;

import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.enums.Status;
import com.telran.gardenshop.exceptionhandler.OrderNotFoundException;
import com.telran.gardenshop.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusService {
    private final OrderRepository orderRepository;
    private static final Logger log = LogManager.getLogger(OrderStatusService.class);

    public OrderStatusService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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

    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        if (order.getStatus() == Status.AWAITING_PAYMENT || order.getStatus() == Status.PENDING) {
            order.setStatus(Status.CANCELLED);
            orderRepository.save(order);
            log.info("Order ID {} has been cancelled", order.getId());
        }
    }
    public Status getOrderStatusById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return order.getStatus();
    }
}

