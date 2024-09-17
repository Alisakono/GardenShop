package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.enums.UserRole;
import com.telran.gardenshop.mapper.UserMapper;
import com.telran.gardenshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto addUser(UserDto userDto) {
        if (userRepository.existsById(userDto.getEmail())) {
            throw new IllegalArgumentException("Ein Benutzer mit dieser E-Mail existiert bereits");
        }
        User user = userMapper.dtoToEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.getPasswordHash()));
        if (userDto.getUserRole() != null) {
            user.setUserRole(userDto.getUserRole());
        } else {
            user.setUserRole(UserRole.USER);
        }
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
    public  void save(User user){
        userRepository.save(user);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteById(email);
    }

    public Optional<User> getByLogin(String login) {
        return userRepository.findById(login);
    }


}
