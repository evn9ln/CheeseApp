package org.cheeseapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/start")
    public String startPage() {
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
