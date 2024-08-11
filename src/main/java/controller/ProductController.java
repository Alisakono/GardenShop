package controller;

import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/garden")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String helloMessage() {
        return "Hello World";
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {

        return service.getAll();
    }
}
