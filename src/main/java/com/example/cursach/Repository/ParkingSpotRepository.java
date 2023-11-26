package com.example.cursach.Repository;

import com.example.cursach.Model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Integer> {
    public List<ParkingSpot> findParkingSpotsByStatus(String status);
}
