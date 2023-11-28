package com.example.cursach.Controller;

import com.example.cursach.Model.Client;
import com.example.cursach.Service.RegistrationService;
import com.example.cursach.util.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final ClientValidation clientValidation;
    @Autowired
    public AuthController(RegistrationService registrationService, ClientValidation clientValidation) {
        this.registrationService = registrationService;
        this.clientValidation = clientValidation;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("client")Client client) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        clientValidation.validate(client, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationService.registration(client);
        return "redirect:/auth/login";
    }
}
