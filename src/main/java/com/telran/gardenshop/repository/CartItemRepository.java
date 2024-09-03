package com.telran.gardenshop.repository;

import com.telran.gardenshop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
