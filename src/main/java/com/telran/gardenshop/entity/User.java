package com.telran.gardenshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.telran.gardenshop.enums.UserRole;
import jakarta.persistence.*;
import jakarta.transaction.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import liquibase.change.ChangeStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.lang.reflect.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Column(name = "name")
    @NotNull
    @Length(max = 45, message = "{validation.author.name}")
    @Pattern(regexp = "[A-Za-z\\s.'-]{1,45}", message = "{validation.author.name}")
    private String name;
    @Id
    @Email(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "{validation.visitor.email}")
    @Size(max = 45, message = "{validation.visitor.email}")
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$",
            message = "{validation.visitor.password}")
    @Column(name = "password_hash")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole userRole=UserRole.USER;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;
private String refreshToken;


}
