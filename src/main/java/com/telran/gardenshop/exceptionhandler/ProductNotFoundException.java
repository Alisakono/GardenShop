package com.telran.gardenshop.exceptionhandler;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product with id " + id + " not found");
    }

    public ProductNotFoundException(String id) {
        super("Product with id " + id + " not found");
    }
}
