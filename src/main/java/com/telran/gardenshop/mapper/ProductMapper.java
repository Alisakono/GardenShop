package com.telran.gardenshop.mapper;


import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    default Product dtoToEntity() {
        return dtoToEntity(null);
    }

    default Product dtoToEntity(ProductRequestDto productRequestDto) {
        return null;
    }

    @Mapping(target = "categoryId",source = "category.id")
    default ProductResponseDto entityToResponseDto() {
        return entityToResponseDto(null);
    }

    ProductResponseDto entityToResponseDto(Product product);

    List<ProductResponseDto> toResponseDtoList(List<Product> products);


    List<ProductRequestDto> toRequestDtoList(List<Product> products);

    ProductRequestDto entityToRequestDto(Product createdProduct);
}
