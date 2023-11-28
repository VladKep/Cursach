package com.example.cursach.Security;

import com.example.cursach.Service.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final ClientDetailService clientDetailService;
    @Autowired
    public AuthProviderImpl(ClientDetailService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails clientDetails = clientDetailService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if(!clientDetails.getPassword().equals(password)) {
            throw new BadCredentialsException("Невірний пароль. Спробуйте ще раз!");
        }
        return new UsernamePasswordAuthenticationToken(clientDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
