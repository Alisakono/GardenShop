package com.telran.gardenshop.repository;

import com.telran.gardenshop.entity.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @NotNull
    Optional<Order> findById(@NotNull Long id);
    @Query("SELECT o FROM Order o WHERE o.status IN ( 'PENDING','SHIPPED','DELIVERED')")
    List<Order>findOrdersToUpdate();
}
