package com.project.delicioso.controller;

import com.project.delicioso.entity.ChangePasswordRequest;
import com.project.delicioso.entity.User;
import com.project.delicioso.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor // permet de ne pas créer le constructeur
@RestController //dit à java que c'est un controller
@RequestMapping("api/v1/users") //définie le endpoint qui va définir la donnée
public class UserController {

    private UserService userService;

    @GetMapping()
    public List<User> getUsers(){ // Method to retrieve the list of users
        return userService.getUsers();
    }

    @GetMapping("/{id}") // Method to retrieve a user by his id
    public User getUser(@PathVariable Long id){
        return  userService.getUser(id);
    }

    @PostMapping() // Method to post a user
    public ResponseEntity<String> postUser(@RequestBody User user){
        return userService.postUser(user);
    }

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}