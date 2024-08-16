package com.telran.gartenshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

 /*   @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)*/
//import jakarta.persistence.Column;
//
//private Product product;
//
//    @Column(nullable = false)
//    private Integer quantity;
   /* private Product product;

    @Column(nullable = false)
    private Integer quantity;


    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }*/

}
