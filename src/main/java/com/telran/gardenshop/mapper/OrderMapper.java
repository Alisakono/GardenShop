package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.*;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    default OrderResponseDto entityToResponseDto(Order order) {
        return new OrderResponseDto();
    }
    Order dtoToEntity(OrderStatusDto orderStatusDto);
    OrderStatusDto entityToDto(Order order);


    @Mapping(source = "deliveryAddress", target = "deliveryAddress")
    @Mapping(source = "deliveryMethod", target = "deliveryMethod")
    Order dtoToEntity(OrderRequestDto orderRequestDto);

    OrderRequestDto entityToRequestDto(Order order);
@Mapping(source = "productId",target = "product.id")
    OrderItem toOrderItem(OrderItemDto itemDto);

    List<OrderItemDto> toOrderItemDtoList(List<OrderItem> items);

    @Named("calculateTotalPrice")
    default BigDecimal calculateTotalPrice(List<OrderItem> items) {
        return items.stream()
                .map(OrderItem::getPriceAtPurchase)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Mapping(source = "id", target = "orderId")
    OrderResponseDto toOrderResponseDto(Order order);
    @Mapping(source = "product.id",target = "productId")
    OrderItemDto toOrderItemDto(OrderItem item);

}




