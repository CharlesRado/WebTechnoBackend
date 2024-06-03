package com.project.delicioso.controller;

import com.project.delicioso.entity.Restaurant;
import com.project.delicioso.services.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("/restaurants") //définie le endpoint qui va définir la donnée
public class RestaurantController {
    private RestaurantService restaurantService;

    @GetMapping() // Method to retrieve the list of restaurants
    public List<Restaurant> getRestaurants(){
        return restaurantService.getRestaurants();
    }

    @GetMapping("/{id}") // Method to retrieve a restaurant by his id
    public Restaurant getRestaurant(@PathVariable Long id){
        return restaurantService.getRestaurant(id);
    }

    @PostMapping() // Method to post a restaurant
    public ResponseEntity<String> postRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.postRestaurant(restaurant);
    }
}
