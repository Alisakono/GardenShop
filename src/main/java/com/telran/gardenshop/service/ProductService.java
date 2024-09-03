package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;

import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import com.telran.gardenshop.repository.ProductRepository;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    //private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository repository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ProductService(ProductRepository repository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void addProduct(ProductRequestDto productRequestDto) {
        Category categoryId = categoryRepository.findById(String.valueOf(productRequestDto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setDiscountPrice(BigDecimal.ZERO);
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setCategory(categoryId);

        repository.save(product);
    }

    public ProductRequestDto updateProduct(Long id,ProductRequestDto productRequestDto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        Category category = categoryRepository.findById(String.valueOf(productRequestDto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found"));


        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());
        product.setCategory(category);
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        Product updatedProduct = repository.save(product);

        return productMapper.entityToRequestDto(updatedProduct);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Optional<Product> getById(Long id) {
       return repository.findById(id);
    }

    public List<ProductResponseDto> getProductsByFilters(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount) {
        List<Product> allProducts = repository.findAll();
        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> categoryId == null || product.getCategory().getCategoryId().equals(categoryId))
                .filter(product -> minPrice == null || product.getPrice().compareTo(minPrice) >= 0)
                .filter(product -> maxPrice == null || product.getPrice().compareTo(maxPrice) <= 0)
                .filter(product -> discount == null || (product.getDiscountPrice() != null && discount) || (product.getDiscountPrice() == null && !discount))
                .toList();

       return filteredProducts.stream().map(productMapper::entityToResponseDto)
               .collect(Collectors.toList());


    }
}



