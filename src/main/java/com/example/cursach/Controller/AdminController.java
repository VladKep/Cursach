package com.example.cursach.Controller;

import com.example.cursach.Service.ClientServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ClientServiceAdmin clientServiceAdmin;

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientServiceAdmin.clients());
        return "admin/seeClients";
    }

    @GetMapping("/clients/{id}")
    public String clientById(@PathVariable("id") int id, Model model) {
        model.addAttribute("client", clientServiceAdmin.clientById(id));
        return "admin/clientInfo";
    }
}
