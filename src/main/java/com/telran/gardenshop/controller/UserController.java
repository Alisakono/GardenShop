package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.UserDto;
import com.telran.gardenshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping
@Validated
@Slf4j
public class UserController {
    private  final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto created = service.addUser(userDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDetails, @PathVariable String email) {
        userDetails.setEmail(email);
        boolean isUpdated = service.updateUser(userDetails);
        if (isUpdated) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDetails, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        service.deleteUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
