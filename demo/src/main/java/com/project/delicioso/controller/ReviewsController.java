package com.project.delicioso.controller;

import com.project.delicioso.entity.Review;
import com.project.delicioso.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("/reviews") //définie le endpoint qui va définir la donnée
public class ReviewsController {

    private ReviewService reviewService;

    @GetMapping() // Method to retrieve the list of reviews
    public List<Review> getReviews(){
        return reviewService.getReviews();
    }

    @GetMapping("/{reviewId}") // Method to retrieve a review by his id
    public Review getReview(@PathVariable Long reviewId){
        return reviewService.getReview(reviewId);
    }

    @PostMapping() // Method to post a review
    public ResponseEntity<String> postReview(@RequestBody Review review){
        return reviewService.postReview(review);
    }

    @GetMapping("/{reviewId}/restaurant-name") // Method to retrieve the restaurant name by the review id
    public String getRestaurantNameByReviewId(@PathVariable Long reviewId){
        return reviewService.getRestaurantNameByReviewId(reviewId);
    }
}
