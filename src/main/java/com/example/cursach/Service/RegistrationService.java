package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final ClientRepository clientRepository;
    // private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registration(Client client) {
//        String encodedPassword = passwordEncoder.encode(client.getPassword());
//        client.setPassword(encodedPassword);
        clientRepository.save(client);
    }
}
