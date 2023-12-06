package com.example.cursach.Service;

import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;
    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingSpot findById(int id) {
        return parkingSpotRepository.findById(id).orElseThrow();
    }

    public void change(int id, String status) {
        ParkingSpot parkingSpot = findById(id);
        parkingSpot.setStatus(status);
        parkingSpotRepository.save(parkingSpot);
    }
}
