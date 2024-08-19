package com.telran.gardenshop.controller;


import com.telran.gardenshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telran.gardenshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> all = service.getAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public List<Product> getAllByName(@RequestParam String name) {

        return service.getAllByName(name);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        boolean isUpdated = service.updateProduct(product);
        if (isUpdated) {
            return new ResponseEntity<>(isUpdated ? HttpStatus.OK : HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
