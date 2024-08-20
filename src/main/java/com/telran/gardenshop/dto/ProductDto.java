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

    @NotNull(message = "Product name should be not null")
    @Length(max = 45, message = "Product name should be not null and no more than 45 characters")
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
    private BigDecimal discountPrice;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
