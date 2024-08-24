package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {

    private String categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal discountPrice;

}
//category, minPrice, maxPrice, discount, sort


