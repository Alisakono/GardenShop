package com.telran.service;

import com.telran.entity.Category;
import com.telran.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {

        return repository.findAll();
    }

    public Optional<Category> getById(Long id) {
        return repository.findById(id);
    }

    public Category add(Category category) {
        Category createCategory = repository.save(category);
        return repository.save(createCategory);
    }

    public Category updateCategory(Category category) {
        if (repository.existsById(category.getId())) {
            repository.save(category);
        } else {
            return null;
        }

        return category;
    }


    public void remove(Long id) {
        Category category = repository.findById(id).get();
        repository.delete(category);
    }
}

