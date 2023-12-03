package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final ClientRepository clientRepository;
    @Autowired
    public RegistrationService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void registration(Client client) {
        String role = "ROLE_USER";
        client.setRole(role);
        clientRepository.save(client);
    }
}
