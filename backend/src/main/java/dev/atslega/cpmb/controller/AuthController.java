package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.AdminRequest;
import dev.atslega.cpmb.dto.AuthenticationRequest;
import dev.atslega.cpmb.dto.AuthenticationResponse;
import dev.atslega.cpmb.dto.UserResponse;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Role;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.AuthenticationService;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.util.AdminMapper;
import dev.atslega.cpmb.util.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserMapper userMapper;
    private final AdminMapper adminMapper;
    private final AuthenticationService authenticationService;

    @Value("${disableRegister:false}")
    private boolean disableRegister;

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        var token = authenticationService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid AdminRequest request) {
        if (disableRegister) return ResponseEntity.badRequest().build();

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
