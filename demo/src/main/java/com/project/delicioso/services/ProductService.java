package com.project.delicioso.services;

import com.project.delicioso.entity.Product;
import com.project.delicioso.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getProducts(){ // Method to retrieve the list of products
        return productRepository.findAll();
    }

    public Product getProduct(Long productId){ // Method to retrieve a product by his id
        return productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product does not exist"));
    }

    @Transactional(readOnly = true) // Method to retrieve the restaurant name by the product id
    public String getRestaurantNameByProductId(Long productId) {
        return productRepository.findRestaurantNameByProductId(productId);
    }
}
