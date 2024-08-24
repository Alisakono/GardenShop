package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.mapper.CategoryMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryService {
    private static final Logger logger = LogManager.getLogger(CategoryService.class);
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToEntity(categoryDto);
        Category createCategory = repository.save(category);
        return categoryMapper.entityToDto(createCategory);
    }

    public CategoryDto updateCategory( CategoryDto categoryDto) {
        String categoryId = categoryDto.getCategoryId();
        Optional<Category> optional = repository.findById(categoryId);
        if (optional.isPresent()) {
            Category category = categoryMapper.dtoToEntity(categoryDto);
            Category savedCategory = repository.save(category);
            return categoryMapper.entityToDto(savedCategory);
        } else {
            return null;
        }
    }

    public void remove(Category categoryId) {
        repository.delete(categoryId);
    }

    public List<CategoryDto> getAll() {
        List<Category> categories= repository.findAll();
        return categoryMapper.entityToDtoList(categories);
    }
}

