package com.example.cursach.Controller;

import com.example.cursach.Service.ClientServiceAdmin;
import com.example.cursach.Service.ParkingSpotService;
import com.example.cursach.Security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("")
    public String mainPage() {
        return "client/main";
    }

    @GetMapping("/available")
    public String availableSpots(Model model) {
        System.out.println(parkingSpotService.availableParkingSpots());
        model.addAttribute("available", parkingSpotService.availableParkingSpots());
        return "client/freeSpots";
    }

    @GetMapping("/my-info")
    public String userInfo(@AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        model.addAttribute("client", clientDetails);
        return "client/clientInfo";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin/mainAdmin";
    }
}
