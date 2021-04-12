package org.cheeseapp.controllers;

import org.cheeseapp.domain.Product;
import org.cheeseapp.domain.User;
import org.cheeseapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepo.findByLogin(login);
       // System.out.println(auth.toString());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/changeprof")
    public String changeProf(User user, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User userFromDb = userRepo.findByLogin(login);
        userFromDb.set(user);
        userRepo.save(userFromDb);

        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String login2 = auth.getName();
        User userFromDb2 = userRepo.findByLogin(login2);
        model.addAttribute("user", userFromDb2);
        return "redirect:/profile";
    }
}
