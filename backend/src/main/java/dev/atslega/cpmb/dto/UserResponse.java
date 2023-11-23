package dev.atslega.cpmb.dto;

import lombok.Data;

@Data
public class UserResponse {

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String role;
}