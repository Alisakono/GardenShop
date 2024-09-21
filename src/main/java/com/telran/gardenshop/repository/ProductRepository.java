package com.telran.gardenshop.repository;

import com.telran.gardenshop.dto.ProductRequestDto;
import com.telran.gardenshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE "
            + "(:hasCategory = FALSE OR p.category.categoryId = :category) AND "
            + "(:minPrice IS NULL OR p.price >= :minPrice) AND "
            + "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "
            + "(:discount IS NULL OR (p.discountPrice IS NOT NULL AND :discount = TRUE) OR (p.discountPrice IS NULL AND :discount = FALSE)) ")
    Page<ProductRequestDto> findProductsByFilters(Pageable pageable);


}




