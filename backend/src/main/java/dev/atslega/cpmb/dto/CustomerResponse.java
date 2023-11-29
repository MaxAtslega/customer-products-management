package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerResponse {

    private String firstName;

    private String lastName;

    private String email;
}
