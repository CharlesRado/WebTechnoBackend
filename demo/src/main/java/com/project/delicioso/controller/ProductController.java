package com.project.delicioso.controller;

import com.project.delicioso.entity.Product;
import com.project.delicioso.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("api/v1/products") //définie le endpoint qui va définir la donnée
public class ProductController {

    private ProductService productService;

    @GetMapping() // Method to retrieve the list of products
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{productId}") // Method to retrieve a product by order id
    public Product getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }

    @GetMapping("/{productId}/restaurant-name") // Method to retrieve the restaurant name by product id
    public String getRestaurantNameByProductId(@PathVariable Long productId) {
        return productService.getRestaurantNameByProductId(productId);
    }
}
