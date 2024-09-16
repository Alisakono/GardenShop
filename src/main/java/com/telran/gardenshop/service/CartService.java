package com.telran.gardenshop.service;


import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.ItemRequest;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.exceptionhandler.ProductNotFoundException;
import com.telran.gardenshop.mapper.CartMapper;
import com.telran.gardenshop.repository.CartRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;


@Service
@Transactional
public class CartService {
    private final UserRepository userRepository;
    private final ProductRepository repository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;


    @Autowired
    public CartService(UserRepository userRepository, ProductRepository repository, CartRepository cartRepository, CartMapper cartMapper) {
        this.userRepository = userRepository;
        this.repository = repository;
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Transactional
    public CartDto addProductToCart(@NotNull ItemRequest itemRequest, String email) {
        Optional<Product> product = repository.findById(Long.valueOf(itemRequest.getProductId()));
        Optional<User> user = Optional.ofNullable(userRepository.findUsersByEmail(email));

        Cart cart = user.get().getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user.get());
        }
        Set<CartItem> items = cart.getItems();
        boolean found = false;
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.get().getId())) {
                item.setQuantity(itemRequest.getQuantity() + itemRequest.getQuantity());
                found = true;
                break;
            }
        }
        if (!found) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product.get());
            cartItem.setQuantity(itemRequest.getQuantity());
            cartItem.setCart(cart);
            items.add(cartItem);
        }
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.entityToDto(savedCart);
    }

    public CartDto getCartByEmail(String email) {
        User user = userRepository.findUsersByEmail(email);
        Cart cart = cartRepository.findByUser(user);
        return cartMapper.entityToDto(cart);
    }
}

    

