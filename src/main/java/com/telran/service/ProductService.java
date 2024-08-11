package com.telran.service;

import com.telran.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telran.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {

        return repository.getAll();
    }
    public List<Product> getAllByName(String name) {
        return repository.getAll().stream().filter(n->n.getName().startsWith(name)).toList();
    }

}
