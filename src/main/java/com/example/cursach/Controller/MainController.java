package com.example.cursach.Controller;

import com.example.cursach.ClientService.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/available")
    public String availableSpots(Model model) {
        System.out.println(parkingSpotService.availableParkingSpots());
        model.addAttribute("available", parkingSpotService.availableParkingSpots());
        return "hello";
    }
}
