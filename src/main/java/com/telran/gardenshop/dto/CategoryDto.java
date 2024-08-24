package com.telran.gardenshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    @NotNull(message = "{validation.category.name}" )
    @Length(max = 45, message = "{validation.category.name}")
    private String categoryId;
    @NotNull(message = "{validation.category.name}" )
    @Length(max = 45, message = "{validation.category.name}")
    private String categoryName;
  //  private List<ProductResponseDto> products;
}
