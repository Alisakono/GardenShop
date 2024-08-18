package com.telran.gardenshop.controller;

import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getCategories() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Category> getById(@PathVariable Long id) {
       return service.getById(id);
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.add(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (category.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.updateCategory(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
