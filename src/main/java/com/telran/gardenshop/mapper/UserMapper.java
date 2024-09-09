package com.telran.gardenshop.mapper;


import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);

}
