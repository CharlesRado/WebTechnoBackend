package com.project.delicioso.repository;

import com.project.delicioso.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Query to find the restaurant name by the product id
    @Query("SELECT p.restaurant.name FROM Product p WHERE p.productId = :productId")
    String findRestaurantNameByProductId(@Param("productId") Long productId);
}
