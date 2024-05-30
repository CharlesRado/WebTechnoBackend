package com.project.delicioso.repository;

import com.project.delicioso.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    // Query to find the delivery name
    @Query("SELECT d FROM Delivery d WHERE d.deliveryName = :deliveryName")
    List<Delivery> findDeliveriesByDeliveryName(@Param("deliveryName") String deliveryName);

    // Query to find the location
    @Query("SELECT d.location FROM Delivery d")
    List<String> findAllLocations();

    // Query to find the restaurant name by the order id
    @Query("SELECT p.restaurant.name FROM Delivery p WHERE p.deliveryId = :deliveryId")
    String findRestaurantNameByDeliveryId(@Param("deliveryId") Long deliveryId);

    // Query to find the restaurant name by the order id
    @Query("SELECT p.order.id FROM Delivery p WHERE p.deliveryId = :deliveryId")
    String getOrderIdByDeliveryId(@Param("deliveryId") Long deliveryId);
}
