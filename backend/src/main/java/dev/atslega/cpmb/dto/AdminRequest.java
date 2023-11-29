package dev.atslega.cpmb.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminRequest {

    @NotEmpty(message = "{required.field}")
    private String lastName;

    @NotEmpty(message = "{required.field}")
    private String firstName;

    @NotEmpty(message = "{required.field}")
    private String email;

    @NotEmpty(message = "{required.field}")
    private String password;

    @NotEmpty(message = "{required.field}")
    private String company_name;

    @NotEmpty(message = "{required.field}")
    private String company_address;
}
