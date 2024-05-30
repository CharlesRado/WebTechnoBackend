package com.project.delicioso.services;


import com.project.delicioso.entity.Order;
import com.project.delicioso.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> getOrders(){ // Method to retrieve the list of orders
        return orderRepository.findAll();
    }

    public Order getOrder(Long orderId){ // Method to retrieve an order by his id
        return orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order does not exist"));
    }

    @Transactional(readOnly = true) // Method to retrieve the restaurant name by the order id
    public String getRestaurantNameByOrderId(Long orderId){
        return orderRepository.findRestaurantNameByOrderId(orderId);
    }

    @Transactional(readOnly = true) // Method to retrieve the product name by the order id
    public String getProductNameByOrderId(Long orderId){
        return orderRepository.findProductNameByOrderId(orderId);
    }
}
