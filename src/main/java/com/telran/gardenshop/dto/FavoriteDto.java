package com.telran.gardenshop.dto;

import com.telran.gardenshop.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoriteDto {
    private Long favoriteId;
    private String email;
    private Product product;
}
