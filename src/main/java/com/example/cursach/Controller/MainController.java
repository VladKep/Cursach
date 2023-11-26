package com.example.cursach.Controller;

import com.example.cursach.ClientService.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/available")
    public String availableSpots() {
        System.out.println(parkingSpotService.availableParkingSpots());
        return "hello";
    }
}
