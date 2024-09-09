package com.telran.gardenshop.service;


import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.ItemRequest;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.CartMapper;
import com.telran.gardenshop.repository.CartRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public CartDto addProductToCart(ItemRequest itemRequest, String email) {

        Product product = repository.findById(Long.valueOf(itemRequest.getProductId()))
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<User> user = Optional.ofNullable(userRepository.findUsersByEmail(email));

        Cart cart = user.get().getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user.get());
        }
        Set<CartItem> items = cart.getItems();
        AtomicReference<Boolean> found = new AtomicReference<>(false);

        items.forEach(cartItem -> {
            if (!cartItem.getProduct().getId().equals(product.getId())) {
                return;
            }
            cartItem.setQuantity(cartItem.getQuantity() + itemRequest.getQuantity());
            found.set(true);
        });

        if (!found.get()) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
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

    

