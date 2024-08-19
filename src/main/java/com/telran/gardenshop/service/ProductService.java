package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
    public List<ProductDto> getAll() {
        List<Product> productList = repository.findAll();
        return productMapper.entityListToDto(productList);
    }

    public List<ProductDto> getAllSorted(Sort sort) {
        return repository.findAll(sort).stream().map(productMapper::entityToDto).toList();
    }

    public Page<ProductDto> getAllByPages(Pageable pageable) {
        return repository.findAll(pageable).map(productMapper::entityToDto);
    }

    public List<Product> getProductsByCategory(Category category) {
        return repository.findProductsByCategory(category);
    }

    public ProductDto add(ProductDto productDto) {
        Product product = productMapper.dtoToEntity(productDto);
        Product createdProduct = repository.save(product);
        return productMapper.entityToDto(createdProduct);
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Optional<Product> optional = repository.findById(productDto.getId());
        if (optional.isPresent()) {
            Product saved = repository.save(productMapper.dtoToEntity(productDto));
            return productMapper.entityToDto(saved);
        } else {
            return null;
        }
    }
    public void remove(Long id){
        repository.deleteById(id);
    }
}
