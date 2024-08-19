package com.telran.gardenshop.mapper;



import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

     Product dtoToEntity(ProductDto productDto);

     ProductDto entityToDto(Product product);

    List<ProductDto> entityListToDto(List<Product> products);
}
