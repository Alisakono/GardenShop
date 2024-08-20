package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.mapper.CategoryMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAll() {
        List<Category> categories = repository.findAll();
        return categoryMapper.entityToDto(categories);
    }

    public CategoryDto add(CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToEntity(categoryDto);
        Category createCategory = repository.save(category);
        return categoryMapper.entityToDto(createCategory);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Long id = categoryDto.getId();
        Optional<Category> optional = repository.findById(id);
        if (optional.isPresent()) {
            Category category = categoryMapper.dtoToEntity(categoryDto);
            Category savedCategory = repository.save(category);
            return categoryMapper.entityToDto(savedCategory);
        } else {
            return null;
        }
    }


    public void remove(Long id) {
        repository.deleteById(id);
    }
}

