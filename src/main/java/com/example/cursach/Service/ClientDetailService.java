package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Repository.ClientRepository;
import com.example.cursach.Security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientDetailService implements UserDetailsService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientDetailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> client = clientRepository.findClientByUsername(username);
        if(client.isEmpty()) {
            throw new UsernameNotFoundException("Користувача не знайдено");
        }
        return new ClientDetails(client.get());
    }
}
