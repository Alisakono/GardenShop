package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.OrderRequestDto;
import com.telran.gardenshop.dto.OrderResponseDto;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToEntity(OrderRequestDto orderRequestDto);
   // @Mapping(source = "cart.cartItem", target = "items")
    OrderResponseDto entityToResponseDto(Order order);


    List<OrderItem> mapItemsToEntities(List<CartDto> orderItems);

    OrderResponseDto toResponseDto(Order order);

    OrderRequestDto entityToDto(Order savedOrder);
}
