package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.CategoryMapper;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import com.telran.gardenshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private static CategoryRepository categoryRepository;
    private static CategoryMapper categoryMapper;
    private static CategoryService categoryService;
@BeforeEach
public void init(){
    categoryRepository = Mockito.mock(CategoryRepository.class);
    categoryMapper = Mappers.getMapper(CategoryMapper.class);
    categoryService = new CategoryService(categoryRepository, categoryMapper);
}
    @Test
  public void addCategory() {
        Category category =new Category();
        category.setCategoryId("8");
        category.setCategoryName("Flowers");
        CategoryDto categoryDto = categoryMapper.entityToDto(category);
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(category);
        categoryRepository.save(category);
        verify(categoryRepository).save(Mockito.any(Category.class));
        assertEquals(category.getCategoryId(), categoryDto.getCategoryId());
        assertEquals(category.getCategoryName(),categoryDto.getCategoryName());

    }

    @Test
    public void updateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId("123");
        categoryDto.setCategoryName("Flowers");

        Category existingCategory = new Category();
        existingCategory.setCategoryId("123");
        existingCategory.setCategoryName("Plants");

        Category updatedCategory = new Category();
        updatedCategory.setCategoryId("123");
        updatedCategory.setCategoryName("Flowers");

        when(categoryRepository.findById(categoryDto.getCategoryId())).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(updatedCategory);


        Category resultCategory = categoryService.updateCategory(categoryDto);
        verify(categoryRepository).save(Mockito.any(Category.class));

        assertEquals(categoryDto.getCategoryId(),resultCategory.getCategoryId());
        assertEquals(categoryDto.getCategoryName(),resultCategory.getCategoryName());
    }

    @Test
    public void remove() {
    String categoryId = "123";
    when(categoryRepository.existsById(categoryId)).thenReturn(true);
    boolean result = categoryService.remove(categoryId);
    verify(categoryRepository).deleteById(categoryId);
    assertTrue(result);
    }

    @Test
    public void getAll() {
    List<Product> products = new ArrayList<>();
        List<Category> categories = List.of(
              new Category("1","New category",products),
                new Category("2","New category2",products)
        );

        when(categoryRepository.findAll()).thenReturn(categories);
        List<CategoryDto> result = categoryService.getAll();
        verify(categoryRepository,times(1)).findAll();
        assertEquals(2,result.size());
        assertEquals("New category",result.get(0).getCategoryName());
        assertEquals("New category2",result.get(1).getCategoryName());

    }
}