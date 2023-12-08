package com.example.cursach.Service;

import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.NoteRepository;
import com.example.cursach.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    private final NoteRepository noteRepository;
    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, NoteRepository noteRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.noteRepository = noteRepository;
    }

    public ParkingSpot findById(int id) {
        return parkingSpotRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void changeSpotStatus() {
        List<Note> notes = noteRepository.findAll();
        LocalDateTime time = LocalDateTime.now();
        for (Note note : notes) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime end = LocalDateTime.parse(note.getEndDate(), formatter);
            if(note.getParkingSpot().getStatus().equals("Зайнято")) {
                if (time.isAfter(end)) {
                    note.getParkingSpot().setStatus("Вільно");
                    noteRepository.save(note);
                }
            }
        }
    }
}
