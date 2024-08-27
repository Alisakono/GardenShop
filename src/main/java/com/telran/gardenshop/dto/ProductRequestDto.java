package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private String categoryId;
    @NotBlank
    private String imageUrl;
    /* "name": "string",
             "description": "string",
             "price": "number",
             "category": "string",
             "image": "string"*/
}
