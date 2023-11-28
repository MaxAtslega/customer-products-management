package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.CustomerRequest;
import dev.atslega.cpmb.dto.CustomerResponse;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.CustomerService;
import dev.atslega.cpmb.util.CustomerMapper;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
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

    @GetMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        List<Customer> customers = customerService.getAllCustomers(authentication.getCompany());
        var resp = customers.stream().map(customerMapper::toResponse).toList();

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.getCustomerById(id, authentication.getCompany()).orElse(null);
        var resp = customerMapper.toResponse(customer);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity deleteCustomerById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        customerService.deleteCustomer(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
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
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerMapper.toModel(request);
        customer.setCompany(companyService.getCompanyById(authentication.getCompany()).orElse(null));

        customer = customerService.saveCustomer(customer);
        var resp = customerMapper.toResponse(customer);

        return ResponseEntity.created(URI.create(customer.getId().toString())).body(resp);
    }
}
