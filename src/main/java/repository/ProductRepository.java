package repository;

import entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    public List<Product> findAll() {
        return Product.products;
    }
}
