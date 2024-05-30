package com.project.delicioso.services;

import com.project.delicioso.entity.Review;
import com.project.delicioso.repository.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public List<Review> getReviews(){ // Method to retrieve the list of reviews
        return reviewRepository.findAll();
    }

    public Review getReview(Long reviewId){ // Method to retrieve a review by his id
        return reviewRepository.findById(reviewId).orElseThrow(()-> new RuntimeException("Review does not exist"));
    }

    public ResponseEntity<String> postReview(Review review){ // Method to add a review
        reviewRepository.save(review);
        return new ResponseEntity<>("ADD", HttpStatus.CREATED);
    }

    @Transactional(readOnly = true) // Method to retrieve the resaurant name by the review id
    public String getRestaurantNameByReviewId(Long reviewId){
        return reviewRepository.findRestaurantNameByReviewId(reviewId);
    }
}
