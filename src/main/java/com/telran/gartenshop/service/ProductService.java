package com.telran.gartenshop.service;

import com.telran.gartenshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.telran.gartenshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public List<Product> getAllByName(String name) {
        return repository.findAll().stream().filter(n -> n.getName().startsWith(name)).toList();
    }

    public void add(Product product) {
        repository.save(product);
    }

    public boolean updateProduct( Product product) {
        Optional<Product> byId = repository.findById(product.getId());
        if (byId.isPresent()) {
            repository.save(product);
            return true;
        } else {
            repository.save(product);
        }
        return false;
    }

    public void remove(Product product) {
repository.deleteById(product.getId());
    }


}
