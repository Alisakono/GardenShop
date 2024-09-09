package com.telran.gardenshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.transaction.Status;
import liquibase.change.ChangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password_hash")
    private String passwordHash;

    public Cart getCart() {
        return null;
    }
    //@Enumerated(EnumType.STRING)
    //@Column(name = "role")
    //private String role;
   //@OneToOne(cascade = CascadeType.DETACH)
   //@JoinColumn(name = "cart_id")
   //private Cart cart;
}
