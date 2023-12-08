package com.example.cursach.Repository;

import com.example.cursach.Model.Note;
import com.example.cursach.Model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    public List<Note> findByParkingSpot(ParkingSpot parkingSpot);
}
