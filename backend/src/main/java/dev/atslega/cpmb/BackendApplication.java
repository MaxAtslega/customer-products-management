package dev.atslega.cpmb;

import dev.atslega.cpmb.model.Company;
import dev.atslega.cpmb.model.User;
import dev.atslega.cpmb.service.UserService;
import dev.atslega.cpmb.model.Role;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendApplication {

    @GetMapping("/healthy")
    public String home() {
        return "Healthy!";
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    /*@Bean
    CommandLineRunner run(UserService userService) {
        return args -> {  // inserting data after application is up
            userService.saveUser(new User("Admin", "Admin", "admin@enterprise.com", "12345678", Role.ADMIN));
        };
    }*/

    @Bean
    public ModelMapper getModelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        return mapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
