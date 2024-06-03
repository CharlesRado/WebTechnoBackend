package com.project.delicioso.authentication;

import com.project.delicioso.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data // provides to generates getters and setters for all fields
@Builder // help to build my object in a easy web using the design pattern Builder
@NoArgsConstructor
@AllArgsConstructor // If we talk about design pattern builder we need to have this one
public class RegisterRequest {

    private String  firstname;
    private String  lastname;
    private String  username;
    private LocalDate dateOfBirth;
    private String  email;
    private String  password;
    private  Role role;
    private String address;
    private String postalCode;
}
