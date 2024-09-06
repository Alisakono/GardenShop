package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.*;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import com.telran.gardenshop.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToEntity(OrderRequestDto orderRequestDto);
   // @Mapping(source = "cart.cartItem", target = "items")


    List<OrderItem> mapItemsToEntities(List<CartDto> orderItems);

    OrderResponseDto toResponseDto(Order order);

    OrderRequestDto entityToDto(OrderRequestDto savedOrder);


    OrderRequestDto entityToRequestDto(Order savedOrder);


   }
