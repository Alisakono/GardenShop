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
import java.util.UUID;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public void addCategory(CategoryDto categoryDto) {
       Category category = new Category();
        category.setCategoryId(UUID.randomUUID().toString());
        category.setCategoryName(categoryDto.getCategoryName());
        repository.save(category);
    }
    public Category updateCategory(CategoryDto categoryDto) {
       Category category = new Category();
       if (categoryDto.getCategoryId()==null){
           category.setCategoryId(UUID.randomUUID().toString());
       }else {
           category.setCategoryId(categoryDto.getCategoryId());
       }
       category.setCategoryName(categoryDto.getCategoryName());
       return repository.save(category);


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

