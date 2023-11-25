package com.example.cursach.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/hello")
    public String greetings(Model model, @RequestParam(value = "name", required = false, defaultValue = "User") String name) {
        model.addAttribute("name", name);
        return "hello";
    }
}
