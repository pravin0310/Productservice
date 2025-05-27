package org.com.productservice.services;

import org.com.productservice.exception.ProductNotFoundException;
import org.com.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        return null;
    }
}
