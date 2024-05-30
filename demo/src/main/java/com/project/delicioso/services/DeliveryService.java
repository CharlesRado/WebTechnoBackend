package com.project.delicioso.services;


import com.project.delicioso.entity.Delivery;
import com.project.delicioso.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryService {

    private DeliveryRepository deliveryRepository;

    public List<Delivery> getDeliveries(){ // Method to retrieve the list of deliveries
        return deliveryRepository.findAll();
    }

    public Delivery getDelivery(Long deliveryId){ // Method to retrieve a delivery by his id
        return deliveryRepository.findById(deliveryId).orElseThrow(()-> new RuntimeException("Delivery does not exist"));
    }

    public List<String> getAllLocations() { // Method to retrieve all delivery locations
        return deliveryRepository.findAllLocations();
    }

    @Transactional(readOnly = true) // Method to retrieve the restaurant name by the delivery id
    public String getRestaurantNameByDeliveryId(Long deliveryId){
        return deliveryRepository.findRestaurantNameByDeliveryId(deliveryId);
    }

    @Transactional(readOnly = true) // Method to retrieve the order id by the delivery id
    public String getOrderIdByDeliveryId(Long deliveryId){
        return deliveryRepository.getOrderIdByDeliveryId(deliveryId);
    }
}
