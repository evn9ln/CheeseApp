package org.cheeseapp.controllers;


import org.cheeseapp.domain.Role;
import org.cheeseapp.domain.User;
import org.cheeseapp.repos.UserRepo;
import org.cheeseapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!UserService.addUser(user, userRepo)) {
            model.addAttribute("message", "existingUser");
            return "registration";
        }
        return "redirect:/login";
    }

}
