package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.UserDto;
import com.telran.gardenshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/garden")
public class UserController {
    private  final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = service.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDto>getUserByEmail(@PathVariable String email){
        Optional<UserDto>optionalUserDto=service.getUserByEmail(email);
        return optionalUserDto.map(ResponseEntity::ok)
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto created = service.addUser(userDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{email}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody @Valid UserDto userDetails) {
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

    @GetMapping("/search")
    public List<UserDto> getUserByName(@RequestParam String name) {
        return service.getUserByName(name);
    }

    @DeleteMapping("/roles")
    public ResponseEntity<Void> deleteAllRoles() {
        service.deleteAllRoles();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
