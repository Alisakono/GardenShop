package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import com.telran.gardenshop.repository.ProductRepository;
import jakarta.validation.Valid;
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

    public List<ProductRequestDto> getAll() {

      // logger.debug("Product retrieved from DB: {}", ()->products.stream().map(Product::getName).toList());
         List<Product> products = repository.findAll();
        return productMapper.toRequestDtoList(products);
    }

    public List<ProductRequestDto> getAllSorted(Sort sort) {
        return repository.findAll(sort).stream().map(productMapper::entityToRequestDto).toList();
    }

    public Page<ProductRequestDto> getAllByPages(Pageable pageable) {
        return repository.findAll(pageable).map(productMapper::entityToRequestDto);
    }

    public ProductRequestDto add(@Valid ProductRequestDto productRequestDto) {
        Product product =productMapper.dtoToEntity(productRequestDto);
        Product createdProduct = repository.save(product);
        return productMapper.entityToRequestDto(createdProduct);
    }

    public ProductResponseDto updateProduct(Long id,ProductRequestDto productRequestDto) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            Product saved = repository.save(productMapper.dtoToEntity(productRequestDto));
            return productMapper.entityToResponseDto(saved);
        } else {
            return null;
        }
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}

