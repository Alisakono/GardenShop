package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.enums.Status;
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
    private List<OrderItemDto> items;
    @NotBlank(message = "Delivery address is required")
    private String deliveryAddress;

    @NotBlank(message = "Delivery method is required")
    private String deliveryMethod;
    private Status status;



}
