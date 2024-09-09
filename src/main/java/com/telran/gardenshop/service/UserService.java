package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.UserMapper;
import com.telran.gardenshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User savedUser = userRepository.save(user);

        return userMapper.entityToDto(savedUser);
    }


    @Transactional
    public boolean updateUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update fields
            user.setName(userDto.getName());
            user.setPasswordHash(userDto.getPasswordHash());
            user.setPhoneNumber(userDto.getPhoneNumber());
            // Save updated user
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteById(email);
    }

    public Optional<User> getByLogin(String login) {
        return userRepository.findById(login);
    }


}
