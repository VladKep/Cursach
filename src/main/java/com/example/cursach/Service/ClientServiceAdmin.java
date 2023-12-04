package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceAdmin {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceAdmin(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Client> clients() {
        return clientRepository.findAll();
    }
}
