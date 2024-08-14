package com.telran.service;

import com.telran.entity.Cart;
import com.telran.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Optional<Cart> getCartByUser(String user) {
        return repository.findAll().stream()
                .filter(cart -> cart.getUser().equals(user))
                .findFirst();
    }

    public void add(Cart cart) {
        repository.save(cart);
    }

    public boolean updateCart(Cart cart) {
        Optional<Cart> optional = repository.findById(cart.getUser());
        if (optional.isPresent()) {
            repository.save(cart);
            return true;
        } else {
            return false;
        }
    }


    public void deleteByUser(String user) {
        repository.deleteById(user);
    }
}
    

