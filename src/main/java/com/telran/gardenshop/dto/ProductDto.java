package com.telran.gardenshop.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotNull(message = "{validation.product.name}")
    @Length(max = 45, message =  "{validation.product.name}")
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private BigDecimal discountPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
