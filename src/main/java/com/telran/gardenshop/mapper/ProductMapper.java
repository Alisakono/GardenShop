package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductResponseDto entityToResponseDto(Product product);

    ProductResponseDto entityToResponseDto(ProductRequestDto products);
     default ProductDto entityToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(String.valueOf(product.getId()));
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        return dto;
    }



    @Mapping(source = "categoryId", target = "category.categoryId")
    List<Product> toResponseDtoList(List<Product> products);

    List<Product> toRequestDtoList(List<Product> products);

    ProductResponseDto entityToResponseDto(Optional<Product> product, HttpStatus httpStatus);

    // @Mapping(source = "category.categoryId", target = "categoryId")
    ProductRequestDto entityToRequestDto(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount, Sort sort);
    // @Mapping(source = "categoryId", target = "category.categoryId")

    ProductResponseDto entityToResponseDto(String category, BigDecimal minPrice, BigDecimal maxPrice, Boolean discount, Sort sort);

    Product dtoToEntity(Long id, ProductRequestDto productRequestDto);


    ProductRequestDto entityToRequestDto(Optional<Product> product, HttpStatus httpStatus);

    default Product dtoToEntity(ProductRequestDto productRequestDto) {
        return null;
    }

   // @Mapping(target = "id", source = "product.id")
    @Mapping(source = "category.categoryId", target = "categoryId")
    ProductRequestDto entityToRequestDto(Product product);

}
