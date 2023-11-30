package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.*;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Product;
import dev.atslega.cpmb.model.Role;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.AuthenticationService;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.util.AdminMapper;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import dev.atslega.cpmb.util.UserMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;
    private final UserMapper userMapper;


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