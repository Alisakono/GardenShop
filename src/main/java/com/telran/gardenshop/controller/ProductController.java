package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.service.ProductService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/product")
@Table(name = "products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>>getProductsByFilters(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean discount,
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({@SortDefault(sort = "name")})
           Pageable pageable){
        Page<ProductResponseDto> productsByFilters = service.getProductsByFilters(
               pageable,category, minPrice, maxPrice, discount);
        return ResponseEntity.ok(productsByFilters);
    }

    @PostMapping("")
    public ResponseEntity<Void> addProduct(@RequestBody @Validated ProductRequestDto productRequestDto) {
        service.addProduct(productRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductRequestDto> updateProduct(@PathVariable Long id, @RequestBody @Validated ProductRequestDto productRequestDto) {
        ProductRequestDto updatedProduct = service.updateProduct(id, productRequestDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        return service.remove(id);
    }
}
