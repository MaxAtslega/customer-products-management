package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.ErrorResponse;
import dev.atslega.cpmb.dto.UserRequest;
import dev.atslega.cpmb.dto.UserResponse;
import dev.atslega.cpmb.model.Role;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import dev.atslega.cpmb.util.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing user accounts")
public class UserController {

    private final UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Retrieve All Users",
            description = "Fetch a list of all users, with pagination options.")
    public ResponseEntity<List<UserResponse>> getUser(@RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                                                      @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        List<User> users = userService.getAllUser(authentication.getCompany(), size, pageNumber);
        var resp = users.stream().map(userMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    @RolesAllowed({"ADMIN"})
    @Operation(summary = "Create New User",
            description = "Add a new user to the system. Accessible only by admin users.")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        User user = userMapper.toModel(request);
        user.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));
        user.setRole(Role.USER);
        user = userService.saveUser(user);

        var resp = userMapper.toResponse(user);
        return ResponseEntity.created(URI.create(user.getId().toString())).body(resp);
    }

    @Operation(summary = "Delete User by ID",
            description = "Remove a user from the system using their unique ID. This operation is irreversible and accessible only by admin users.")
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        userService.deleteUser(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Retrieve User by ID",
            description = "Find and return a specific user using their unique ID.")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserById(id, authentication.getCompany()).orElse(null);
        var resp = userMapper.toResponse(user);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Update User",
            description = "Update the details of an existing user. Accessible only by admin users.")
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