package com.telran.gardenshop.entity;

import jakarta.persistence.*;
import liquibase.logging.mdc.customobjects.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.patterns.Pointcut;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private String deliveryAddress;
    private String contactPhone;
    private String deliveryMethod;
    @Column(name = "status")
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

   /* @OneToMany(fetch = FetchType.LAZY,mappedBy = "cart_item")
    private List<CartItem> cartItems;*/


}
