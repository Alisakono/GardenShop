package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.CategoryDto;
import com.telran.gardenshop.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
   public abstract Category dtoToEntity(CategoryDto categoryDto);
   public abstract CategoryDto entityToDto(Category category);
 /* public ProductRequestDto productToProductRequestDto(Product product) {
      return Mappers.getMapper(ProductMapper.class).entityToRequestDto(product);

  }
*/
    public abstract List<CategoryDto> entityToDtoList(List<Category> categories);

}


