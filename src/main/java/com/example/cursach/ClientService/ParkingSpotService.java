package com.example.cursach.ClientService;

import com.example.cursach.Model.ParkingSpot;
import com.example.cursach.Repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    @Autowired
    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public List<ParkingSpot> availableParkingSpots() {
        return parkingSpotRepository.findParkingSpotsByStatus("Вільно");
    }
}
