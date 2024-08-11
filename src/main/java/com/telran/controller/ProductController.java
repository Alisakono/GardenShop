package com.telran.controller;

import com.telran.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.telran.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/garden")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String helloMessage() {
        return "Hello World";
    }

    @GetMapping("/all")
    public List<Product> getAll() {

        return service.getAll();
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
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
        service.remove(product);
        return new ResponseEntity<>(product, HttpStatus.ACCEPTED);
    }
}
