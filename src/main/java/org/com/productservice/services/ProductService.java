package org.com.productservice.services;

import org.com.productservice.exception.ProductNotFoundException;
import org.com.productservice.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;;
    List<Product> getAllProducts();
    Product createProduct(Product product);
    ResponseEntity<Void> deleteProduct(Long id);
}
