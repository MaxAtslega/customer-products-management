package dev.atslega.cpmb.dto;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String lastName;

    private String firstName;

    private String email;

    private String role;
}