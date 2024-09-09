package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.CartItem;
import com.telran.gardenshop.entity.OrderItem;
import jakarta.transaction.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long orderId;
    List<OrderItem> items;
    private String productId;
    private Integer quantity;
    private String deliveryAddress;
    private String deliveryMethod;
    private LocalDateTime createdAt;

}
