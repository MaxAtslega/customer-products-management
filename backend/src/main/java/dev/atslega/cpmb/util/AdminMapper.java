package dev.atslega.cpmb.util;

import dev.atslega.cpmb.dto.AdminRequest;
import dev.atslega.cpmb.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminMapper {

    private final ModelMapper mapper;

    public User toModel(AdminRequest request) {
        return mapper.map(request, User.class);
    }

    public AdminRequest toRequest(User user) {
        return mapper.map(user, AdminRequest.class);
    }
}