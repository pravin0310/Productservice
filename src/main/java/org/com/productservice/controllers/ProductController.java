package org.com.productservice.controllers;

import org.com.productservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return new Product();
    }

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return new ArrayList<>();
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product){
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        System.out.println("Deleteed product with id:" + id);
    }
}
