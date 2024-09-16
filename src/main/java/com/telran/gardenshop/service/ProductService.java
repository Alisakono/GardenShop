package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;

import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.exceptionhandler.ProductNotFoundException;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import com.telran.gardenshop.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    //private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository repository;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepository repository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.productMapper = productMapper;

    }

    public void addProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        repository.save(product);
        productMapper.entityToDto(product);
    }

    public ProductRequestDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        Product updatedProduct = repository.save(product);
        return productMapper.entityToRequestDto(updatedProduct);
    }

    public ProductRequestDto getById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.entityToRequestDto(product);

    }

    public Page<ProductResponseDto> getProductsByFilters(Pageable pageable, String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount) {
        Page<Product> productPage = repository.findAll(pageable);
        List<ProductResponseDto> allProducts = productPage
                .getContent()
                .stream()
                .map(productMapper::entityToResponseDto)
                .toList();
        List<ProductResponseDto> filteredProducts = allProducts.stream()
                .filter(product -> categoryId == null || product.getCategoryId().equals(categoryId))
                .filter(product -> minPrice == null || product.getPrice().compareTo(minPrice) >= 0)
                .filter(product -> maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0)
                .filter(product -> discount == null || (product.getDiscountPrice() != null && discount) || (product.getDiscountPrice() == null && !discount))
                .toList();
        return new PageImpl<>(filteredProducts, pageable, productPage.getTotalElements());
    }

    public ResponseEntity<Product> remove(Long id) {
        repository.deleteById(id);
        return null;
    }
}



