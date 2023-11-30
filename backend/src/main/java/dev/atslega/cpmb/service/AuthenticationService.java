package dev.atslega.cpmb.service;

import dev.atslega.cpmb.exception.AuthenticationFailedException;
import dev.atslega.cpmb.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String authenticate(String email, String password) {
        try {
            // The authentication manager provides secure authentication and throws exception if it fails
            var authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(authToken);
            var user = (User) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);
            return token;
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Invalid Email or Password");
        }
    }

}
