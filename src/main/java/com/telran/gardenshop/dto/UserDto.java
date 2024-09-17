package com.telran.gardenshop.dto;


import com.telran.gardenshop.entity.Cart;
import com.telran.gardenshop.entity.Favorite;
import com.telran.gardenshop.entity.Order;
import com.telran.gardenshop.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import liquibase.change.ChangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Timer;

import javax.management.relation.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String passwordHash;
    private String name;
    private String phoneNumber;
    private UserRole userRole;

}
