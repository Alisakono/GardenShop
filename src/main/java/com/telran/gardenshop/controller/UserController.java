package com.telran.gardenshop.controller;

import com.telran.gardenshop.dto.JwtRequest;
import com.telran.gardenshop.dto.JwtRequestRefresh;
import com.telran.gardenshop.dto.JwtResponse;
import com.telran.gardenshop.dto.UserDto;
import com.telran.gardenshop.security.AuthService;
import com.telran.gardenshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto userDto) {
            UserDto savedUser = userService.addUser(userDto);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

    }

    @PostMapping("/user")
    @Operation(summary = "Create user")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        UserDto createdUser = userService.addUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    @Operation(summary = "Create login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/token")
    @Operation(summary = "Get new access token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("/refresh")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Get new refresh token")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody JwtRequestRefresh request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PutMapping("/{email}")
    @Operation(summary = "Update user")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDetails, @PathVariable String email) {
        userDetails.setEmail(email);
        boolean isUpdated = userService.updateUser(userDetails);
        if (isUpdated) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{email}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Delete user by email")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
