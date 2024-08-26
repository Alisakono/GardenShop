package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
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

    @GetMapping("")
    @Operation(summary = "Retrieve all product")
    public List<ProductRequestDto> getAll() {
        return service.getAll();
    }
    @GetMapping("/withSort")
    public List<ProductRequestDto> getAll(@SortDefault(sort = "name", direction = Sort.Direction.ASC) Sort sort){
        return service.getAllSorted(sort);
    }

    @GetMapping("/pages")
    public Page<ProductRequestDto> getAll(@PageableDefault(size = 5)
                                   @SortDefault.SortDefaults({@SortDefault(sort = "name")})
                                   Pageable pageable){
        return service.getAllByPages(pageable);
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
        ProductResponseDto updatedProduct = service.updateProduct(id, product);
       return new ResponseEntity<>(updatedProduct !=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteById(@RequestParam Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
