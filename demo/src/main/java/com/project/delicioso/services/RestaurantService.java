package com.project.delicioso.services;

import com.project.delicioso.entity.Restaurant;
import com.project.delicioso.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants(){ // Method to retrieve the list of restaurants
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurant(Long id){ // Method to retrieve the restaurant by his id
        return restaurantRepository.findById(id).orElseThrow(()-> new RuntimeException("rest does not exist"));
    }

    public ResponseEntity<String> postRestaurant(Restaurant restaurant){ // Method to post a restaurant
        restaurantRepository.save(restaurant);
        return new ResponseEntity<>("ADD", HttpStatus.CREATED);
    }


}
