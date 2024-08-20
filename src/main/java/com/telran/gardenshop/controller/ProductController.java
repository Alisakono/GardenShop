package com.telran.gardenshop.controller;


import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.telran.gardenshop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping
@Validated
@Slf4j
@Tag(name = "Product Controller", description = "Actions with product")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    @Operation(summary = "Retrieve all product")
    public List<ProductDto> getAll() {
        return service.getAll();
    }
    @GetMapping("/withSort")
    public List<ProductDto> getAll(@SortDefault(sort = "name", direction = Sort.Direction.DESC) Sort sort){
        return service.getAllSorted(sort);
    }

    @GetMapping("/pages")
    public Page<ProductDto> getAll(@PageableDefault(size = 10)
                                   @SortDefault.SortDefaults({@SortDefault(sort = "name")})
                                   Pageable pageable){
        return service.getAllByPages(pageable);
    }
    @GetMapping("/{category}")
    public List<Product> getProductsByCategory(@PathVariable Category category){
        return service.getProductsByCategory(category);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> addProduct(@RequestBody @Valid ProductDto product) {
        ProductDto createdProduct = service.add(product);
        if (product.getName() == null || product.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto product) {
        ProductDto updatedProduct = service.updateProduct(product);
        return new ResponseEntity<>(product, updatedProduct !=null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
