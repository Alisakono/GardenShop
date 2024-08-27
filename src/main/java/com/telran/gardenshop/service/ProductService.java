package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    public ProductRequestDto add( ProductRequestDto productRequestDto) {
        Product product =productMapper.dtoToEntity(productRequestDto);
        Product createdProduct = repository.save(product);
        return productMapper.entityToRequestDto(createdProduct);
    }

    public ProductRequestDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Optional<Product> optional = repository.findById(id);
        Product updatedProduct = null;
        if (optional.isPresent())
            updatedProduct = repository.save(new Product());
        return productMapper.entityToRequestDto(updatedProduct);

    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public ProductResponseDto getById(Long productId) {
        Optional<Product> product = repository.findById(productId);
        return productMapper.entityToResponseDto(product, HttpStatus.OK);
    }

    public List<ProductResponseDto> getProductsByFilters(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount, String sort) {
        List<Product> products = repository.findProductsByFilters(categoryId, minPrice, maxPrice, discount, sort);
        return products.stream().map(productMapper::entityToResponseDto).collect(Collectors.toList());
    }


}

