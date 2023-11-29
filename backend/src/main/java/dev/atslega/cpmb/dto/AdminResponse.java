package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminResponse {

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String company;

    private String company_address;
}
