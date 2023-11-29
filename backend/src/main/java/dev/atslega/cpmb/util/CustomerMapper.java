package dev.atslega.cpmb.util;

import dev.atslega.cpmb.dto.CustomerRequest;
import dev.atslega.cpmb.dto.CustomerResponse;
import dev.atslega.cpmb.dto.UserRequest;
import dev.atslega.cpmb.dto.UserResponse;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper mapper;

    public Customer toModel(CustomerRequest request) {
        return mapper.map(request, Customer.class);
    }

    public CustomerRequest toRequest(Customer customer) {
        return mapper.map(customer, CustomerRequest.class);
    }

    public CustomerResponse toResponse(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
    }
}