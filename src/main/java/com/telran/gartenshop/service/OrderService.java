package com.telran.gartenshop.service;

import com.telran.gartenshop.entity.Order;
import com.telran.gartenshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository repository;

    public List<Order> getAllOrders() {

        return repository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return repository.findById(id);
    }

    public Order createOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(Order order) {
        if (repository.existsById(order.getId())) {
            return repository.save(order);
        }
        return null;
    }


    public boolean deleteOrder(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
