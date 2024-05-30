package com.project.delicioso.repository;

import com.project.delicioso.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // class responsible to communicate with the database
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find/retrieve a user by his email
    Optional<User> findByEmail(String email);
}
