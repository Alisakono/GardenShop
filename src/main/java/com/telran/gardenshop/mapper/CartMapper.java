package com.telran.gardenshop.mapper;
import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.dto.CartItemDto;
import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
    public interface CartMapper {
     default CartDto entityToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getCartId());
        dto.setItems(cart.getItems().stream().map(this::itemToDto).collect(Collectors.toSet()));
        return dto;
    }

    private CartItemDto itemToDto(CartItem cartItem) {
        CartItemDto dto = new CartItemDto();
        dto.setProductName(cartItem.getProduct().getName());
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }


}
