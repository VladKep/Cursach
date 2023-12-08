package com.example.cursach.Service;

import com.example.cursach.Model.Client;
import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.ClientRepository;
import com.example.cursach.Repository.NoteRepository;
import com.example.cursach.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final NoteRepository noteRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ParkingSpotRepository parkingSpotRepository, NoteRepository noteRepository, ClientRepository clientRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.noteRepository = noteRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void reservation(Note note) {
        noteRepository.save(note);
    }

    public List<ParkingSpot> availableParkingSpots() {
        return parkingSpotRepository.findParkingSpotsByStatus("Вільно");
    }

    @Transactional
    public void update(int id, Client client) {
        Client client1 = clientRepository.findById(id).orElseThrow();
        client1.setUsername(client.getUsername());
        client1.setPhoneNumber(client.getPhoneNumber());
        client1.setEmail(client.getEmail());
        client1.setPassword(client.getPassword());
        clientRepository.save(client1);
    }
}
