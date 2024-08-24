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
        CategoryDto add = service.add(category);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @PutMapping("/{category_id}")
    @Operation(summary = "Update categories")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("category_id") String categoryId, @RequestBody @Valid CategoryDto category) {
        if (categoryId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setCategoryId(categoryId);
        CategoryDto updatedCategory = service.updateCategory(category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }


    @DeleteMapping("/{category_id}")
    @Operation(summary = "Delete categories")
    public ResponseEntity<?> deleteCategory(@PathVariable("category_id") Category categoryId) {
        if (categoryId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.remove(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}