package com.telran.repository;

import com.telran.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public List<Product> getAll() {
        return Product.products;
    }

}
