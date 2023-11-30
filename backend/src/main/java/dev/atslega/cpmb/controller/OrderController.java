package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.OrderRequest;
import dev.atslega.cpmb.dto.OrderResponse;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.CustomerService;
import dev.atslega.cpmb.service.OrderService;
import dev.atslega.cpmb.service.ProductService;
import dev.atslega.cpmb.util.EmailAuthenticationToken;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(orderService.getAllOrders(authentication.getCompany()));
    }

    @GetMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OrderResponse order = orderService.getOrderById(id, authentication.getCompany());
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        orderService.deleteOrder(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Company company = companyService.getCompanyById(authentication.getCompany()).orElse(null);

        Customer customer = customerService.getCustomerById(request.getCustomer(), authentication.getCompany());

        Order order = new Order();
        order.setCustomer(customer);
        order.setCompany(company);
        order.setProducts(Arrays.stream(request.getProducts()).map(productId ->
                productService.getProductById(productId, authentication.getCompany())).toList());

        order = orderService.saveOrder(order);

        OrderResponse orderResponse = orderService.mapOrdersToOrderResponse(order);
        return ResponseEntity.created(URI.create(order.getId().toString())).body(orderResponse);
    }

    @PutMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request, @PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        Company company = companyService.getCompanyById(authentication.getCompany()).orElse(null);
        Customer customer = customerService.getCustomerById(request.getCustomer(), authentication.getCompany());

        Order order = new Order();
        order.setCustomer(customer);
        order.setCompany(company);
        order.setId(id);
        order.setProducts(Arrays.stream(request.getProducts()).map(productId ->
                productService.getProductById(productId, authentication.getCompany())).toList());

        order = orderService.updateOrder(order, authentication.getCompany());

        OrderResponse orderResponse = orderService.mapOrdersToOrderResponse(order);
        return ResponseEntity.created(URI.create(order.getId().toString())).body(orderResponse);
    }
}