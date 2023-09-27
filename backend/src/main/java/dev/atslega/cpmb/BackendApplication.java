package dev.atslega.cpmb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendApplication {

    @RequestMapping("/")
    public String home() {
        return "Healthy!";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
