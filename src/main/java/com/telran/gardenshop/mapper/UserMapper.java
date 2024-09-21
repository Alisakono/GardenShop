package com.telran.gardenshop.mapper;


import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
   // @Mapping(source = "userRole", target = "userRole")
    User dtoToEntity(UserDto userDto);
  //  @Mapping(source = "userRole", target = "userRole")
    UserDto entityToDto(User user);

}
