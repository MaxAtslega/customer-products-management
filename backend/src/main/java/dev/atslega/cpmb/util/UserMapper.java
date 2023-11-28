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
        return mapper.map(user, UserResponse.class);
    }
}