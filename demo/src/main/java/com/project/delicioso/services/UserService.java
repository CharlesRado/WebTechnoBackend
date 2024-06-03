package com.project.delicioso.services;

import com.project.delicioso.entity.ChangePasswordRequest;
import com.project.delicioso.entity.User;
import com.project.delicioso.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public List<User> getUsers(){ // Method to retrieve the list of users
        return userRepository.findAll();
    }
    public User getUser(Long id){ // Method to retrieve a user by his id
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User does not exist"));
    }

    public ResponseEntity<String> postUser(User user){ // Method to post a user
        userRepository.save(user);
        return new ResponseEntity<>("ADD", HttpStatus.CREATED);
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }


    /*
    public String getRoleName(Long userId) { // Method to retrieve the role name of a user by his id
        // Récupérer l'utilisateur et retourner le nom du rôle associé
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getRole() != null) {
            return user.getRole().getRole_name();
        } else {
            throw new RuntimeException("Role not found for user");
        }
    }*/
}