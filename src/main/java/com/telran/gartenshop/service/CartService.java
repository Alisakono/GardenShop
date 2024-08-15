package com.telran.gartenshop.service;

import com.telran.gartenshop.entity.Cart;
import com.telran.gartenshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Cart getCartByUser(String user) {
        Optional<Cart> cart = repository.findById(user);
        return new Cart();
    }

    public void add(Cart cart) {
        repository.save(cart);

    }
    public boolean updateCart(String user, Cart cart) {
        Optional<Cart> optional = repository.findById(user);
        if (optional.isPresent()) {
            Cart existingCart = optional.get();
           /* existingCart.setItems(updateCart.getItems);
            existingCart.setTotalPrice(updatedCart.getTotalPrice());*/
            repository.save(existingCart);
            return true;
        } else {
            return false;
        }
    }
    public void deleteByUser(String user) {
        repository.deleteById(user);
    }
}
    

