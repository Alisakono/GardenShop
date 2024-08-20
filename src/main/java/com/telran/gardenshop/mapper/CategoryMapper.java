package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Category;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
   public abstract Category dtoToEntity(CategoryDto categoryDto);
   public abstract CategoryDto entityToDto(Category category);
   public ProductDto productToProductDto(Product product){
       return Mappers.getMapper(ProductMapper.class).entityToDto(product);
   }
   public abstract List<CategoryDto> entityToDto(List<Category> categories);
}
