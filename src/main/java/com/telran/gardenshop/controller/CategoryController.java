package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequestMapping("/categories")
@Tag(name = "Category Controller", description = "Actions with categories")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Retrieve all category")
    public List<CategoryDto> getCategories() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create categories")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CategoryDto add = service.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update categories")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (category.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CategoryDto updateCategory = service.updateCategory(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete categories")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
