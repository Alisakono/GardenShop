package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotEmpty
    private List<CartDto> items;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private String deliveryMethod;
}
