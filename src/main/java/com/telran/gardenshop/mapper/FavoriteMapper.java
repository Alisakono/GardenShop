package com.telran.gardenshop.mapper;

import com.telran.gardenshop.dto.ProductResponseDto;
import com.telran.gardenshop.entity.Favorite;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    List<ProductResponseDto> entityListToDto(List<Favorite> favorites);


}
