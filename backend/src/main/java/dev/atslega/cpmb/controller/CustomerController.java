package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.service.CustomerService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id).orElse(null);
        if(customer == null){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message","Customer not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Long id) {

        Customer customer = customerService.getCustomerById(id).orElse(null);
        if(customer == null){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message","Customer not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        customerService.deleteCustomer(customer);
        return ResponseEntity.ok("Ok!");
    }

    @PostMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }
}
