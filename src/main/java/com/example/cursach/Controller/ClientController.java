package com.example.cursach.Controller;

import com.example.cursach.Model.Client;
import com.example.cursach.Service.ClientService;
import com.example.cursach.Security.ClientDetails;
import com.example.cursach.Service.ClientServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    private ClientServiceAdmin clientServiceAdmin;

    @GetMapping("")
    public String mainPage() {
        return "client/main";
    }

    @GetMapping("/available")
    public String availableSpots(Model model) {
        System.out.println(clientService.availableParkingSpots());
        model.addAttribute("available", clientService.availableParkingSpots());
        return "client/freeSpots";
    }

    @GetMapping("/my-info")
    public String userInfo(@AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        model.addAttribute("client", clientDetails.getClient());
        return "client/clientInfo";
    }

    @GetMapping("/my-info/edit")
    public String edit(@AuthenticationPrincipal ClientDetails clientDetails, Model model) {
        model.addAttribute("client", clientDetails.getClient());
        return "client/clientInfoEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("client")Client client, @PathVariable("id") int id) {
        clientService.update(id, client);
        return "redirect:/my-info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "admin/mainAdmin";
    }
}
