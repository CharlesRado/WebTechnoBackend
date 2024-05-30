package com.project.delicioso.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // provides to generates getters and setters for all fields
@Builder // help to build my object in a easy web using the design pattern Builder
@NoArgsConstructor
@AllArgsConstructor // If we talk about design pattern builder we need to have this one
public class RegisterRequest {

    private String  firstname;
    private String  lastname;
    private String  email;
    private String  password;
}
