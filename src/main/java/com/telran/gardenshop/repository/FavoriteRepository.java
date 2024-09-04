package com.telran.gardenshop.repository;

import com.telran.gardenshop.entity.Favorite;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserEmail(String email);
    boolean existsByUserAndProduct(User user, Product product);
}


