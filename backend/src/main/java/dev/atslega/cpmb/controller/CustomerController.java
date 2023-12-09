package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.CustomerRequest;
import dev.atslega.cpmb.dto.CustomerResponse;
import dev.atslega.cpmb.dto.ErrorResponse;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.CustomerService;
import dev.atslega.cpmb.util.CustomerMapper;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Management", description = "Endpoints for managing customer information")
public class CustomerController {

    private final CustomerService customerService;
    private final CompanyService companyService;

    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerController(CustomerService customerService, CompanyService companyService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.companyService = companyService;
        this.customerMapper = customerMapper;
    }

    @Operation(summary = "Find all customers", description = "Get all costumers")
    @GetMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                                                                  @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        List<Customer> customers = customerService.getAllCustomers(authentication.getCompany(), size, pageNumber);
        var resp = customers.stream().map(customerMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Find customer by id", description = "Find customer by id",
            responses = {@ApiResponse(responseCode = "404", description = "Book not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.getCustomerById(id, authentication.getCompany());
        var resp = customerMapper.toResponse(customer);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Delete Customer by ID",
            description = "Remove a customer from the system using their unique ID. This operation is irreversible.")
    @ApiResponse(responseCode = "200", description = "Customer successfully deleted")
    @ApiResponse(responseCode = "404", description = "Customer not found",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        customerService.deleteCustomer(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Update customer info", description = "Update customer info",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Invalid Request data",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
                    @ApiResponse(responseCode = "404", description = "Book not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
            })
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") Long id, @RequestBody @Valid CustomerRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var customer = customerMapper.toModel(request);
        customer.setId(id);
        customer.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));
        customer = customerService.updateCustomer(customer, authentication.getCompany());
        var resp = customerMapper.toResponse(customer);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Create New Customer", description = "Add a new customer to the database.")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerMapper.toModel(request);
        customer.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));

        customer = customerService.saveCustomer(customer);
        var resp = customerMapper.toResponse(customer);

        return ResponseEntity.created(URI.create(customer.getId().toString())).body(resp);
    }
}
