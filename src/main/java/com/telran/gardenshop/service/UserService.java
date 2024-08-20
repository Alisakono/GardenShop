package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.UserMapper;
import com.telran.gardenshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return userMapper.entityListToDto(users);
    }
    public List<UserDto> getUserByName(String name) {
        List<User> users = userRepository.findUsersByName(name);
        return userMapper.entityListToDto(users);
    }

    public UserDto addUser(UserDto userDto) {
        User user = userMapper.dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser);
    }

    public boolean updateUser(UserDto userDto) {
        Optional<User> optional = userRepository.findById(userDto.getEmail());
        if (optional.isPresent()) {
            userRepository.save(userMapper.dtoToEntity(userDto));
            return true;
        } else {
            return false;
        }
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteById(email);
    }

    public void deleteAllRoles() {
        userRepository.deleteAll();
    }
}
