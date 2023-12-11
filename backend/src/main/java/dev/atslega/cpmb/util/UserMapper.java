package dev.atslega.cpmb.util;

import dev.atslega.cpmb.dto.UserRequest;
import dev.atslega.cpmb.dto.UserResponse;
import dev.atslega.cpmb.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toModel(UserRequest request) {
        return mapper.map(request, User.class);
    }

    public UserRequest toRequest(User user) {
        return mapper.map(user, UserRequest.class);
    }

    public UserResponse toResponse(User user) {
        String companyName = user.getCompany() != null ? user.getCompany().getCompanyName() : null;
        String companyAddress = user.getCompany() != null ? user.getCompany().getCompanyAddress(): null;

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setLastName(user.getLastName());
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        response.setCompanyName(companyName);
        response.setCompanyAddress(companyAddress);

        return response;
    }
}