package com.telran.gardenshop.repository;

import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByCategory(Category category);

}


