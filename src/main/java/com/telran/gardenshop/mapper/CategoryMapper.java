package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
   public abstract Category dtoToEntity(CategoryDto categoryDto);
   public abstract CategoryDto entityToDto(Category category);
  /*public ProductResponseDto productToProductDto(Product product) {
      return Mappers.getMapper(ProductMapper.class).entityToResponseDto(product);

  }*/

    public abstract List<CategoryDto> entityToDtoList(List<Category> categories);

}


