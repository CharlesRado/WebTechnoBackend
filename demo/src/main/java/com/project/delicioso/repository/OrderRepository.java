package com.project.delicioso.repository;

import com.project.delicioso.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Query to find the restaurant name by the order id
    @Query("SELECT p.restaurant.name FROM Order p WHERE p.orderId = :orderId")
    String findRestaurantNameByOrderId(@Param("orderId") Long orderId);

    // Query to find the product name by the order id
    @Query("SELECT p.product.productName FROM Order p WHERE p.orderId = :orderId")
    String findProductNameByOrderId(@Param("orderId") Long orderId);
}
