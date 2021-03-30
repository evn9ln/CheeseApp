package org.cheeseapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class StartController {

    @GetMapping("/start")
    public String startPage(@RequestParam(value = "name", required = false) String name, Model model) {

        if(name == null) {
            model.addAttribute("message", "Hello, Guest!");
        }
        else {
            model.addAttribute("message", "Hello, " + name + "!");
        }

        return "first/start";
    }

    @GetMapping("/authorization")
    public String authorization() {
        return "first/authorization";
    }

    @GetMapping("/registration")
    public String registation() {
        return "first/registration";
    }

}
