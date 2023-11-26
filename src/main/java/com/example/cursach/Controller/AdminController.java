package com.example.cursach.Controller;

import com.example.cursach.AdminService.ClientServiceAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ClientServiceAdmin clientServiceAdmin;

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientServiceAdmin.clients());
        return "admin/main";
    }
}
