package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;

import com.telran.gardenshop.entity.Product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.telran.gardenshop.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@Validated
@Slf4j
@Tag(name = "Product Controller", description = "Actions with product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    @Operation(summary = "Retrieve product by id")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> product = service.getById(id);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getProductsByFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean discount
            ) {
        List<ProductResponseDto> productsByFilters = service.getProductsByFilters(category, minPrice, maxPrice, discount);
        return new ResponseEntity<>(productsByFilters, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<Void> addProduct(@RequestBody @Validated ProductRequestDto productRequestDto) {
        if (productRequestDto == null || productRequestDto.getPrice() == null || productRequestDto.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            service.addProduct(productRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
  @PutMapping("/{id}")
  public ResponseEntity<ProductRequestDto> updateProduct(@PathVariable Long id,@RequestBody @Validated ProductRequestDto productRequestDto) {
     try {
        ProductRequestDto updatedProduct = service.updateProduct(id, productRequestDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
