package com.example.cursach.util;

import com.example.cursach.Service.ClientDetailService;
import com.example.cursach.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidation implements Validator {
    private final ClientDetailService clientDetailService;
    @Autowired
    public ClientValidation(ClientDetailService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client) target;
        try {
            clientDetailService.loadUserByUsername(client.getUsername());
        } catch (UsernameNotFoundException e) {
            return;
        }
        errors.rejectValue("username", "", "Користувач с таким ім'ям вже існує!");
    }
}
