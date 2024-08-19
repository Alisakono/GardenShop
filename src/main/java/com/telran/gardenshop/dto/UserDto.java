package com.telran.gardenshop.dto;


import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.Favorite;
import com.telran.gardenshop.entity.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "{validation.user.email}")
    @Size(max = 255, message = "{validation.user.email}")
    private String email;

    @Size(max = 16, message = "{validation.user.passwordHash}")
    private String passwordHash;

    @Size(max = 45, message = "{validation.user.name}")
    private String name;

    @Size(max = 45, message = "{validation.user.phoneNumber}")
    private String phoneNumber;

    private Role role;
   private Cart cart;
   private Order order;
   private Favorite favorite;
}
