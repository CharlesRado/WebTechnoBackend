package com.project.delicioso.authentication;

import com.project.delicioso.entity.Role;
import com.project.delicioso.entity.User;
import com.project.delicioso.repository.UserRepository;
import com.project.delicioso.configuration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService; // used to create/generate the token using the user object "jwtToken"
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        System.out.println("registering user" + request.getEmail()); // Debug log
        var user = User.builder() // create a user object out of RegisterRequest
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .username(request.getUsername())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // encode before saving in our database
                .role(request.getRole())
                .address(request.getAddress())
                .postalCode(request.getPostalCode())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        System.out.println("JWT Token: " + jwtToken); // Debug log
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // it means that the user is authenticated
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // if oth of them are correct we just need to generate a token and send it back
        var user = userRepository.findByEmail((request.getEmail()))
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
