package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
private Long id;
private String name;
private String description;
  private BigDecimal price;
  private String categoryId;
  private String imageUrl;
  private BigDecimal discountPrice;

}



