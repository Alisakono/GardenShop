package com.telran.gardenshop.repository;

import com.telran.gardenshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE "
            + "(:categoryId IS NULL OR p.category = :categoryId) AND "
            + "(:minPrice IS NULL OR p.price >= :minPrice) AND "
            + "(:maxPrice IS NULL OR p.price <= :maxPrice) AND "
            + "(:discount IS NULL OR (p.discountPrice IS NOT NULL AND :discount = TRUE) OR (p.discountPrice IS NULL AND :discount = FALSE)) "
            + "ORDER BY "
            + "CASE WHEN :sort = 'priceAsc' THEN p.price END ASC, "
            + "CASE WHEN :sort = 'priceDesc' THEN p.price END DESC, "
            + "CASE WHEN :sort = 'discount' THEN p.discountPrice END DESC")

    List<Product> findProductsByFilters( @Param("categoryId") String categoryId,
                                         @Param("minPrice") BigDecimal minPrice,
                                         @Param("maxPrice") BigDecimal maxPrice,
                                         @Param("discount") Boolean discount,
                                         @Param("sort") String sort);
}




