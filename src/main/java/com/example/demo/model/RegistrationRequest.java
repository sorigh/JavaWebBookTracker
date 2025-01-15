package com.example.demo.model;

import lombok.Data;

// kind of like a dto with password
@Data
public class RegistrationRequest {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;
}