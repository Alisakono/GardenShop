package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartItemDto {
    @NotNull
    private String productName;
    @NotNull
    private Integer quantity;


}
