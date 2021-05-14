package org.cheeseapp.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {

    @GetMapping("/home")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Guest") String name, Model model) {
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping
    public String main(Model model) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) { return "mainLoggedIn"; }
        return "main";
    }
}
