package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.http.HttpStatusCode;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    @NotBlank
   private List<OrderItem> items;
    private String deliveryAddress;
    @NotBlank
    private String deliveryMethod;
}
