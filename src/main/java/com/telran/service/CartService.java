package com.telran.service;
import com.telran.entity.Cart;
import com.telran.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Optional<Cart> getCartByUser(String username) {
        return repository.findAll().stream()
                .filter(cart -> cart.getUser().equals(username))
                .findFirst();
    }

    public void add(Cart cart) {
        repository.save(cart);
    }
    public boolean updateCart(Cart cart) {
        Optional<Cart> optional = repository.findById(cart.getId());
        if (optional.isPresent()) {
            repository.save(cart);
            return true;
        } else {
            return false;
        }
    }

    public Cart getCartById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
