package com.telran.gardenshop.repository;

import com.telran.gardenshop.dto.FavoriteDto;
import com.telran.gardenshop.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f JOIN f.product p WHERE f.user.email = :email")
    List<Favorite> findByUserEmail(@Param("email") String email);
}
