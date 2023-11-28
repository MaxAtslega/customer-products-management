package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotEmpty(message = "{required.field}")
    private String firstName;

    @NotEmpty(message = "{required.field}")
    private String lastName;

    @NotEmpty(message = "{required.field}")
    private String email;
}
