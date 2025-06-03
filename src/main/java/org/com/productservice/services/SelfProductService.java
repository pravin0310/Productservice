package org.com.productservice.services;

import org.com.productservice.exception.CategoryNotFoundException;
import org.com.productservice.exception.ProductNotFoundException;
import org.com.productservice.models.Category;
import org.com.productservice.models.Product;
import org.com.productservice.repository.CategoryRepository;
import org.com.productservice.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public  class SelfProductService implements ProductService {

    private final ProductService productService;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {

//        Optional<Product> productOptional = productRepository.findById(id);
//
//        if(productOptional.isEmpty()) {
//            throw new  ProductNotFoundException("Product not found With Id: " + id);
//        }
//
//        return productOptional.get();

        //or

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(id, "Product with id " + id + " doesn't exist."));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) throws CategoryNotFoundException {
        Category category = product.getCategory();

        if(category==null){
            throw new CategoryNotFoundException("Product cant create beacuse Category not found");
        }

        Optional<Category> optionalCategory = categoryRepository.findByTitle(category.getTitle());

        if(optionalCategory.isEmpty()){
            category = categoryRepository.save(category);
        }else{
            category = optionalCategory.get();
        }

        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
