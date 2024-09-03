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

    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        repository.save(category);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        repository.findById(categoryDto.getCategoryId());
        categoryDto.setCategoryId(categoryDto.getCategoryId());
        categoryDto.setCategoryName(categoryDto.getCategoryName());
        Category updatedCategory = repository.save(new Category());
        return categoryMapper.entityToDto(updatedCategory);

    }

    public boolean remove(String categoryId) {
        if (repository.existsById(categoryId)) {
            repository.deleteById(categoryId);
            return true;
        } else {
            return false;
        }
    }


    public List<CategoryDto> getAll() {
        List<Category> categories = repository.findAll();
        return categoryMapper.entityToDtoList(categories);
    }
}

