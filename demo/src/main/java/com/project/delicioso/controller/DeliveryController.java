package com.project.delicioso.controller;

import com.project.delicioso.entity.Delivery;
import com.project.delicioso.services.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("/deliveries") //définie le endpoint qui va définir la donnée
public class DeliveryController {

    private DeliveryService deliveryService;

    @GetMapping() // Method to retrieve the list of deliveries
    public List<Delivery> gerDeliveries(){
        return deliveryService.getDeliveries();
    }

    @GetMapping("/{deliveryId}") // Method to retrieve a delivery by order id
    public Delivery getDelivery(@PathVariable Long deliveryId){
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/locations") // Method to retrieve all delivery locations
    public ResponseEntity<List<String>> getLocations(){
        List<String> locations = deliveryService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{deliveryId}/restaurant-name") // Method to retrieve the restaurant name by the delivery id
    public String getRestaurantNameByDeliveryId(@PathVariable Long deliveryId){
        return deliveryService.getRestaurantNameByDeliveryId(deliveryId);
    }

    @GetMapping("/{deliveryId}/order-id") // Method to retrieve the order id by the delivery id
    public String getOrderIdByDeliveryId(@PathVariable Long deliveryId){
        return deliveryService.getOrderIdByDeliveryId(deliveryId);
    }
}
