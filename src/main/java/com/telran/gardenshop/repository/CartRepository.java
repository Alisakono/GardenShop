package com.telran.gardenshop.repository;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}






