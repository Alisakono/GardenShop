package com.telran.controller;

import com.telran.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.telran.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/garden")
public class ProductController {
    private List<Product> products;
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
    public List<Product> getAllProducts() {

        return service.getAll();
    }
    @GetMapping("/searchByName")
    public List<Product> getAllByName(@RequestParam String name) {

        return service.getAllByName(name);
    }
}
