package com.telran.service;

import com.telran.entity.CartItem;
import com.telran.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository repository;

    @Autowired
    public CartItemService(CartItemRepository repository) {
        this.repository = repository;
    }


    public List<CartItem> getCartItemsByCartId(Long cartId) {
        return repository.findAllByCartId(cartId);
    }


    public void addOrUpdateCartItem(CartItem cartItem) {
        repository.save(cartItem);
    }


    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
