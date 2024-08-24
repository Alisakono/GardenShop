package com.telran.gardenshop.mapper;
import com.telran.gardenshop.dto.CartDto;
import com.telran.gardenshop.entity.Cart;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
    public interface CartMapper {

    Cart dtoToEntity(CartDto cartDto);
    CartDto entityToDto(Cart cart);

}
