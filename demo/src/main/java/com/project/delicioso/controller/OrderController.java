package com.project.delicioso.controller;

import com.project.delicioso.entity.Order;
import com.project.delicioso.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("api/v1/orders") //définie le endpoint qui va définir la donnée
public class OrderController {

    private OrderService orderService;

    @GetMapping()
    public List<Order> getOrders(){ // Method to retrieve the list of orders
        return orderService.getOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId){ // Method to retrieve the order by his id
        return orderService.getOrder(orderId);
    }

    @GetMapping("/{orderId}/restaurant-name") // Method to retrieve the restaurant name by order id
    public String getRestaurantNameByOrderId(@PathVariable Long orderId){
        return orderService.getRestaurantNameByOrderId(orderId);
    }

    @GetMapping("/{orderId}/product-name") // Method to retrieve the product name by order id
    public String getProductNameByOrderId(@PathVariable Long orderId){
        return orderService.getProductNameByOrderId(orderId);
    }
}
