package com.example.cursach.Service;

import com.example.cursach.Model.Note;
import com.example.cursach.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
