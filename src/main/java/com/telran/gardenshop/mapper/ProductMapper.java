package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductResponseDto entityToResponseDto(Product product);

    List<ProductResponseDto> toResponseDtoList(List<Product> products);

    List<ProductRequestDto> toRequestDtoList(List<Product> products);

    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductRequestDto entityToRequestDto(Product createdProduct);

    Product productRequestDtoToEntity(ProductRequestDto productRequestDto);

    Product productResponseDtoToEntity(ProductResponseDto productResponseDto);

    Product dtoToEntity(ProductRequestDto productRequestDto);
}
