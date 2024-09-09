package com.telran.gardenshop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotNull(message = "Delivery address is required")
    private String deliveryAddress;

    private String contactPhone;

    private String deliveryMethod;
    @Column(name = "status",nullable = false)
    private String status="PENDING";
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
    @OneToMany
    private Set<OrderItem> items;
    @ManyToOne
    @JoinColumn(name = "email")
    private User users;

    @PrePersist
    @PreUpdate
    public void updateTimestamps() {
        Timestamp now = Timestamp.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        if (this.createdAt == null) {
            this.createdAt = now;
        }
        this.updatedAt = now;
    }





}
