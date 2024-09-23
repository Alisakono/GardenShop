package com.telran.gardenshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.security.JwtProvider;
import com.telran.gardenshop.service.CategoryService;
import com.telran.gardenshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @MockBean
    private ProductService service;
    @MockBean
    private JwtProvider jwtProvider;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void addProduct() throws Exception {
        ProductRequestDto product = new ProductRequestDto();
        product.setCategoryId(1L);
        product.setDiscountPrice(BigDecimal.valueOf(10));
        product.setImageUrl("Image");
        product.setPrice(BigDecimal.valueOf(20));
        product.setDescription("Description");
        product.setName("Product 1");
        when(service.addProduct(any(ProductRequestDto.class))).thenReturn(new ProductResponseDto());
        mockMvc.perform(post("/product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsBytes(product)))
                .andExpect(status().isCreated());
        verify(service).addProduct(any(ProductRequestDto.class));


    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void updateProductTest() throws Exception {
        ProductRequestDto request = new ProductRequestDto();
       request.setName("Product name");
       request.setCategoryId(2L);
       request.setDescription("Description");
       request.setImageUrl("Image");
       request.setPrice(BigDecimal.valueOf(12.99));
       request.setDiscountPrice(BigDecimal.valueOf(11.99));


        when(service.updateProduct(Mockito.eq(1L), any(ProductRequestDto.class)))
                .thenReturn(request);

       mockMvc.perform(MockMvcRequestBuilders.put("/product/1")
                       .contentType("application/json")
                       .content(objectMapper.writeValueAsString(request)))
                       .andReturn();

       verify(service).updateProduct(Mockito.eq(1L), any(ProductRequestDto.class));

    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void deleteProductTest () throws Exception {
        Long productId = 1L;
        when(service.remove(productId)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.delete("/product/"+productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    @WithMockUser(username = "Test user", roles = {"ADMIN"})
    public void getProductsByFiltersTest() throws Exception{
        Page<ProductResponseDto> productsPage = new PageImpl<>(List.of(
                new ProductResponseDto(1L, "Product 1", BigDecimal.valueOf(10.99), "Category 1", false),
                new ProductResponseDto(2L, "Product 2", BigDecimal.valueOf(15.99), "Category 2", true)
        ));

        when(service.getProductsByFilters(any(), any(), any(), any(), any()))
                .thenReturn(productsPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("category", "Category 1")
                        .param("minPrice", "10")
                        .param("maxPrice", "20")
                        .param("discount", "false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
      verify(service).getProductsByFilters(any(),any(),any(), any(),any());

    }

}