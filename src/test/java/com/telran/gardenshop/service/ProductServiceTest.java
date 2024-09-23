package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.exceptionhandler.ProductNotFoundException;
import com.telran.gardenshop.mapper.ProductMapper;
import com.telran.gardenshop.repository.CategoryRepository;
import com.telran.gardenshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductServiceTest {

    private static ProductRepository repository;

    private static ProductService service;

    private static ProductMapper productMapper;

    @BeforeEach
    public void init() {
        repository = Mockito.mock(ProductRepository.class);
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        productMapper = Mappers.getMapper(ProductMapper.class);
        service = new ProductService(repository, productMapper, categoryRepository, userService);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setName("Test Product");

        ProductRequestDto productRequestDto = productMapper.entityToRequestDto(product);
        when(repository.save(Mockito.any(Product.class))).thenReturn(product);

        ProductResponseDto productResponseDto = service.addProduct(productRequestDto);

        verify(repository,times(1)).save(Mockito.any(Product.class));
        assertEquals(product.getName(), productResponseDto.getName());
    }

    @Test
    void updateProduct() {
        Product newProduct = new Product();
        newProduct.setName("New Name");
        newProduct.setId(1L);

        Product oldProduct = new Product();
        oldProduct.setName("Old Name");
        oldProduct.setId(1L);

        when(repository.findById(newProduct.getId())).thenReturn(Optional.of(oldProduct));
        ProductRequestDto result = service.updateProduct(newProduct.getId(), productMapper.entityToRequestDto(newProduct));

        verify(repository).findById(newProduct.getId());
        assertEquals(newProduct.getName(), result.getName());

        Product nonExistingProduct = new Product();
        nonExistingProduct.setName("Non-Existent");
        nonExistingProduct.setId(12L);

        when(repository.findById(nonExistingProduct.getId())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> {
            service.updateProduct(nonExistingProduct.getId(), productMapper.entityToRequestDto(nonExistingProduct));
        });
    }

    @Test
    void getById() {
        Long productId = 11L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test name");
        when(repository.findById(productId)).thenReturn(Optional.of(product));
        ProductRequestDto result = service.getById(productId);
        Mockito.verify(repository).findById(productId);
        assertNotNull(result);
        assertEquals("Test name",result.getName());

    }

    @Test
    void getProductsByFilters() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(BigDecimal.valueOf(12.99));
      Category category1 = new Category();
      category1.setCategoryId("7");
      product1.setCategory(category1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(BigDecimal.valueOf(13.99));
       Category category2 = new Category();
       category2.setCategoryId("7");
       product2.setCategory(category2);

        Page<Product> mockProductPage = new PageImpl<>(List.of(product1,product2));
        when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(mockProductPage);

        Page<ProductResponseDto> result = service.getProductsByFilters(Pageable.unpaged(),"7",BigDecimal.valueOf(12.99),BigDecimal.valueOf(13.99),true);
        Mockito.verify(repository).findAll(Mockito.any(Pageable.class));

        assertNotNull(result);
        assertEquals(2,result.getTotalElements());
        assertTrue(result.getContent().stream().allMatch(p->p.getCategoryId().equals("7")));
        assertTrue(result.getContent().stream().allMatch(p->p.getPrice().compareTo(BigDecimal.valueOf(10.99))>=0));
        assertTrue(result.getContent().stream().allMatch(p->p.getPrice().compareTo(BigDecimal.valueOf(15))<=0));

    }


    @Test
    void remove() {
        service.remove(2L);
        Mockito.verify(repository).deleteById(2L);
    }
}