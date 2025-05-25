package org.com.productservice.controllers;

import org.com.productservice.models.Product;
import org.com.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productservice;

    public ProductController(ProductService productservice) {
        this.productservice = productservice;
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
//        return new Product();
        return productservice.getProductById(id);
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return productservice.getAllProducts();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
        return null;
    }
}
