package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.*;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Role;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.AuthenticationService;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.util.AdminMapper;
import dev.atslega.cpmb.util.UserMapper;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final AdminMapper adminMapper;

    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUser() {
        List<User> users = userService.getAllUser();
        var resp = users.stream().map(userMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    @RolesAllowed("ADMIN") // needs to enable 'EnableGlobalMethodSecurity' at security class to work
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        var user = userMapper.toModel(request);
        user = userService.saveUser(user);
        var resp = userMapper.toResponse(user);

        return ResponseEntity.created(URI.create(user.getId().toString())).body(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed( {"ADMIN"})
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        User user = userService.getUserById(id).orElse(null);
        if(user == null){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message","User not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(user);
        return ResponseEntity.ok("Ok!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        var token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid AdminRequest request, HttpEntity<byte[]> requestEntity) {
        User user = adminMapper.toModel(request);
        Company company = new Company(request.getCompany_name(), request.getCompany_address());
        company = companyService.saveCompany(company);

        user.setCompany(company);
        user.setRole(Role.ADMIN);
        user = userService.saveUser(user);

        var resp = userMapper.toResponse(user);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(resp);
    }
}