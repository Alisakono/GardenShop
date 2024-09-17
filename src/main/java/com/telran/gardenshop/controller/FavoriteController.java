package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.service.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public ResponseEntity<Void> addProductToFavorites(@RequestParam String email, @RequestParam String productId) {
        favoriteService.addProductToFavorites(email, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/products/{email}")

    public ResponseEntity<List<ProductDto>> getProductsByEmail(@PathVariable String email) {
        List<ProductDto> products = favoriteService.getProductsByEmail(email);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //entfernen email suche
    @GetMapping("/favorites")
    public ResponseEntity<List<ProductDto>> getFavoriteProducts() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<ProductDto> products = favoriteService.getProductsByEmail(email);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}