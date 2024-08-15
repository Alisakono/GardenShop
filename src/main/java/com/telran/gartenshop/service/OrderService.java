package com.telran.gartenshop.service;

import com.telran.gartenshop.entity.OrderInfo;
import com.telran.gartenshop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository repository;

    public List<OrderInfo> getAllOrders() {

        return repository.findAll();
    }

    public Optional<OrderInfo> getOrderById(Long id) {
        return repository.findById(id);
    }

    public OrderInfo createOrder(OrderInfo orderInfo) {
        return repository.save(orderInfo);
    }

    public OrderInfo updateOrder(OrderInfo orderInfo) {
        if (repository.existsById(orderInfo.getId())) {
            return repository.save(orderInfo);
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
