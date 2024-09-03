package com.telran.gardenshop.controller;
import com.telran.gardenshop.dto.FavoriteDto;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.service.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@Validated
@Slf4j

public class FavoriteController {
    private final FavoriteService favoriteService;
    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @GetMapping("/products/{email}")
    public ResponseEntity<List<Product>> getProductsByEmail(@PathVariable String email) {
        List<Product> products = favoriteService.getProductsByEmail(email);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
