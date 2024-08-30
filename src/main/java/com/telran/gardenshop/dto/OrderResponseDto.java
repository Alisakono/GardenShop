package com.telran.gardenshop.dto;

import jakarta.transaction.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private List<CartDto> items = new ArrayList<>();
    private String deliveryAddress;
    private String deliveryMethod;
    private String contactPhone;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
