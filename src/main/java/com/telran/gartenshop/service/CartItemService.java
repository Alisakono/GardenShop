package com.telran.gartenshop.service;

import com.telran.gartenshop.entity.CartItem;
import com.telran.gartenshop.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemService {

    private final CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }


    public Optional<CartItem> getCartItemsByCartId(Long id) {
        return repository.findById(id);
    }


    public void addOrUpdateCartItem(CartItem cartItem) {
        repository.save(cartItem);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
