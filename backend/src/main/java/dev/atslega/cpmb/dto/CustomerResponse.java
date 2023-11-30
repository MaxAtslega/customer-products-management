package dev.atslega.cpmb.dto;

import lombok.Data;

@Data
public class CustomerResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
