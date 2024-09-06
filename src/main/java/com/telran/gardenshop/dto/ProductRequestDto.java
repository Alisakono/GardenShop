package com.telran.gardenshop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ProductRequestDto {
    @NotNull(message = "Name is required")
    @Size(max = 45, message = "Name cannot exceed 45 characters")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotNull
    private BigDecimal discountPrice;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
@NotNull
    private String imageUrl;
}
