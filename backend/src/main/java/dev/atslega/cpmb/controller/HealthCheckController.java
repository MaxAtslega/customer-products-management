package dev.atslega.cpmb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check", description = "Endpoint for checking the health of the service")
@RestController
public class HealthCheckController {

    @Operation(summary = "Health Check",
            description = "Checks if the service is up and running.")
    @ApiResponse(responseCode = "200", description = "Service is healthy and running")
    @GetMapping("/healthy")
    public String home() {
        return "Service is up and running!";
    }
}
