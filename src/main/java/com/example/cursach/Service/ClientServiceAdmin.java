package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Client clientById(int id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void update(int id, Client client) {
        Client client1 = clientRepository.findById(id).orElseThrow();
        client1.setUsername(client.getUsername());
        client1.setPhoneNumber(client.getPhoneNumber());
        client1.setEmail(client.getEmail());
        client1.setRole(client.getRole());
        client1.setPassword(client.getPassword());
        clientRepository.save(client1);
    }
}
