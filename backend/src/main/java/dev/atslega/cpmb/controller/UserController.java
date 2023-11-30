package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.UserRequest;
import dev.atslega.cpmb.dto.UserResponse;
import dev.atslega.cpmb.model.Role;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import dev.atslega.cpmb.util.UserMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<List<UserResponse>> getUser() {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        List<User> users = userService.getAllUser(authentication.getCompany());
        var resp = users.stream().map(userMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        User user = userMapper.toModel(request);
        user.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));
        user.setRole(Role.USER);
        user = userService.saveUser(user);

        var resp = userMapper.toResponse(user);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserById(id, authentication.getCompany()).orElse(null);
        var resp = userMapper.toResponse(user);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = userMapper.toModel(request);
        user.setId(id);
        user.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));
        user = userService.updateUser(user, authentication.getCompany());
        var resp = userMapper.toResponse(user);
        return ResponseEntity.ok(resp);
    }
}