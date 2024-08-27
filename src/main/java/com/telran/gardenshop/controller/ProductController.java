package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.telran.gardenshop.service.ProductService;

import java.util.List;

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

    @GetMapping("/{product_id}")
    @Operation(summary = "Retrieve product by id")
    public ProductResponseDto getById( @PathVariable Long product_id) {
        return service.getById(product_id);
    }

    @GetMapping
    public ResponseEntity <List<ProductResponseDto>> getProductsByFilters(
           /* @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean discount,
            @RequestParam(required = false) String sort*/) {
        List<ProductResponseDto> productsByFilters = service.getProductsByFilters(null, null, null, null, null);
        return new ResponseEntity<>(productsByFilters,HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<ProductRequestDto> addProduct(@RequestBody @Valid ProductRequestDto product) {
        ProductRequestDto createdProduct = service.add(product);
        if (product.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
    }

    @PutMapping("/productId")
    public ResponseEntity<ProductRequestDto> updateProduct(@RequestParam Long id,@RequestBody @Valid ProductRequestDto product) {
        ProductRequestDto updatedProduct = service.updateProduct(id,new ProductRequestDto());
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @DeleteMapping("/productId")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       service.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
