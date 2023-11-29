package dev.atslega.cpmb.controller;

import dev.atslega.cpmb.model.Customer;
import dev.atslega.cpmb.model.Order;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.OrderService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<Object> getOrderById(@PathVariable Long id) {

        Order order = orderService.getOrderById(id).orElse(null);
        if(order == null){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message","Order not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed( {"ADMIN","USER"})
    public ResponseEntity<Object> deleteOrderById(@PathVariable Long id) {

        Order order = orderService.getOrderById(id).orElse(null);
        if(order == null){
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message","Order not found");

            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }

        orderService.deleteOrder(order);
        return ResponseEntity.ok("Ok!");
    }

    @PostMapping("/")
    @RolesAllowed( {"ADMIN","USER"})
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}