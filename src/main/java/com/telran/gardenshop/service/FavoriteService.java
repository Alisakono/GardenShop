package com.telran.gardenshop.service;

import com.telran.gardenshop.dto.ProductDto;
import com.telran.gardenshop.entity.Favorite;
import com.telran.gardenshop.entity.Product;
import com.telran.gardenshop.entity.User;
import com.telran.gardenshop.mapper.FavoriteMapper;
import com.telran.gardenshop.repository.FavoriteRepository;
import com.telran.gardenshop.repository.ProductRepository;
import com.telran.gardenshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteMapper favoriteMapper;

    @Autowired
    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ProductRepository productRepository, FavoriteMapper favoriteMapper) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.favoriteMapper = favoriteMapper;
    }


    public void addProductToFavorites(String email, String productId) {

        User user = userRepository.findUsersByEmail(email);
        user.setEmail(email);

        Product product = productRepository.findById(Long.valueOf(productId))
                .orElseThrow(() -> new IllegalArgumentException("Produkt mit der ID " + productId + " nicht gefunden"));

        boolean exists = favoriteRepository.existsByUserAndProduct(user, product);
        if (exists) {
            throw new IllegalArgumentException("Dieses Produkt ist bereits in den Favoriten des Benutzers.");
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        favoriteRepository.save(favorite);
    }

    public List<ProductDto> getProductsByEmail(String email) {
        List<Favorite> favorites = favoriteRepository.findByUserEmail(email);
        return favorites.stream()
                .map(favorite -> favoriteMapper.productToDto(favorite.getProduct()))
                .collect(Collectors.toList());
    }

}