package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.dto.ErrorResponse;
import dev.atslega.cpmb.dto.OrderRequest;
import dev.atslega.cpmb.dto.OrderResponse;
import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.service.CompanyService;
import dev.atslega.cpmb.service.CustomerService;
import dev.atslega.cpmb.service.OrderService;
import dev.atslega.cpmb.service.ProductService;
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
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Management", description = "Endpoints for managing orders")
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
    @Operation(summary = "Retrieve All Orders",
            description = "Fetch a list of all orders, with options for pagination.")
    @RolesAllowed({"ADMIN", "USER"})
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam(value = "size", required = false, defaultValue = "3") Integer size,
                                                            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(orderService.getAllOrders(authentication.getCompany(), size, pageNumber));
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Retrieve Order by ID",
            description = "Find and return a specific order using its unique ID.")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        OrderResponse order = orderService.getOrderById(id, authentication.getCompany());
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Delete Order by ID",
            description = "Remove an order from the system using its unique ID. This operation is irreversible.")
    @ApiResponse(responseCode = "200", description = "Order successfully deleted")
    @ApiResponse(responseCode = "404", description = "Order not found",
            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {
        EmailAuthenticationToken authentication = (EmailAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        orderService.deleteOrder(id, authentication.getCompany());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Create New Order",
            description = "Add a new order to the system.")
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
    @RolesAllowed({"ADMIN", "USER"})
    @Operation(summary = "Update Order",
            description = "Update the details of an existing order.")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody @Valid OrderRequest request, @PathVariable Long id) {
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