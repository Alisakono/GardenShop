package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface FavoriteMapper {

    @Mapping(source = "id", target = "productId")
    ProductDto productToDto(Product product);

}
