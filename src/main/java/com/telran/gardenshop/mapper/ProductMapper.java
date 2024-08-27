package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Product;
import org.apache.coyote.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    //@Mapping(source = "product.productId", target = "productId")
    //@Mapping(source = "productId", target = "product.productId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductResponseDto entityToResponseDto(Product product);

    List<ProductResponseDto> toResponseDtoList(List<Product> products);

   // @Mapping(source = "product.productId", target = "productId")
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductRequestDto entityToRequestDto(Product product);

    // @Mapping(source = "cartItemId", target = "cartItem.cartItemId")
    List<ProductRequestDto> toRequestDtoList(List<Product> products);


    Product productRequestDtoToEntity(ProductRequestDto productRequestDto);


    Product productResponseDtoToEntity(ProductResponseDto productResponseDto);

    //@Mapping(source = "productId", target = "product.productId")
    @Mapping(source = "categoryId", target = "category.categoryId")
    Product dtoToEntity(ProductRequestDto productRequestDto);

    ProductResponseDto entityToResponseDto(Optional<Product> product, HttpStatus httpStatus);
}
