package com.project.delicioso.repository;

import com.project.delicioso.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Query to find the restaurant name by the review id
    @Query("SELECT p.restaurant.name FROM Review p WHERE p.reviewId = :reviewId")
    String findRestaurantNameByReviewId(@Param("reviewId") Long reviewId);
}
