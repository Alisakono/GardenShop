package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.UserDto;

import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.UserMapper;
import com.telran.gardenshop.repository.CartRepository;
import com.telran.gardenshop.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
    }


    public UserDto addUser(@NotNull UserDto userDto) {
        if (userRepository.existsById(userDto.getEmail())) {
            throw new IllegalArgumentException("A user with this email already exists");
        }
        User user = userMapper.dtoToEntity(userDto);

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDto(savedUser);
    }

    public boolean updateUser(@NotNull UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDto.getName());
            user.setPhoneNumber(userDto.getPhoneNumber());
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
