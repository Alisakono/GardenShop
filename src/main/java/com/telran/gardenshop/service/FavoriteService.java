package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.FavoriteDto;
import com.telran.gardenshop.entity.Favorite;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.mapper.FavoriteMapper;
import com.telran.gardenshop.repository.FavoriteRepository;
import com.telran.gardenshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ProductRepository repository;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.repository = productRepository;
    }

    public List<Product> getProductsByEmail(String email) {
        List<Favorite> favorites = favoriteRepository.findByUserEmail(email);
        return favorites.stream()
                .map(Favorite::getProduct)
                .collect(Collectors.toList());
    }
}



