package com.example.cursach.Service;

import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(int id) {
        return noteRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void changeSpotStatus() {
        List<Note> notes = noteRepository.findAll();
        LocalDateTime time = LocalDateTime.now();
        for (Note note : notes) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime end = LocalDateTime.parse(note.getEndDate(), formatter);
            if (time.isAfter(end)) {
                note.setStatus("Завершено");
                noteRepository.save(note);
            }
        }
    }
}
