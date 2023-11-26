package com.example.cursach.ClientService;

import com.example.cursach.Model.Client;
import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.NoteRepository;
import com.example.cursach.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final NoteRepository noteRepository;
    @Autowired
    public ClientService(ParkingSpotRepository parkingSpotRepository, NoteRepository noteRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.noteRepository = noteRepository;
    }

    public void reservation(LocalDateTime start, LocalDateTime end, Client client, ParkingSpot parkingSpot) {
        Note note = new Note(start, end, client, parkingSpot);
        noteRepository.save(note);
    }
}
