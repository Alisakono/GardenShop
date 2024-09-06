package com.telran.gardenshop.entity;

import jakarta.persistence.*;
import jakarta.transaction.Status;
import liquibase.change.ChangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    private String name;
    @Id
    @Column(name = "email")
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private String role;

    @OneToOne(mappedBy = "user")
    private Cart cart;
}
