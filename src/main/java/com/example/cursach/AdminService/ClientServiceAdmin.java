package com.example.cursach.AdminService;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceAdmin {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientServiceAdmin(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> clients() {
        return clientRepository.findAll();
    }
}
