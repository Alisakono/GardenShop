package com.telran.gardenshop.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @JoinColumn(name ="email")
    @OneToOne
    private User user;

  @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<CartItem> items = new HashSet<>();




}
