package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;

import com.telran.gardenshop.dto.ProductUpdateDto;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.ProductRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    //private static final Logger logger = LogManager.getLogger(ProductService.class);
    private final ProductRepository repository;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;


    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = productMapper.dtoToEntity(productRequestDto);
        Product savedProduct = repository.save(product);
        return productMapper.entityToResponseDto(savedProduct);
    }

    public ProductResponseDto updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product existingProduct = repository.findById(id).orElse(null);
        if (existingProduct != null){
            Product updatedProduct = productMapper.dtoToEntity(id,productUpdateDto);
            updatedProduct = repository.save(updatedProduct);
            return productMapper.entityToResponseDto(updatedProduct);
        }
        return null;

    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public Optional <Product> getById(Long id) {
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

    public ProductResponseDto getById(Long productId) {
        Optional<Product> product = repository.findById(productId);
        return productMapper.entityToResponseDto(product, HttpStatus.OK);
    }

    public List<ProductResponseDto> getProductsByFilters(String categoryId, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount, String sort) {
        List<Product> products = repository.findProductsByFilters(categoryId, minPrice, maxPrice, discount, sort);
        return products.stream().map(productMapper::entityToResponseDto).collect(Collectors.toList());
    }


}



