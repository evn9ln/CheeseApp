package org.cheeseapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {
    @GetMapping("/start")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Guest") String name, Model model) {
        model.addAttribute("name", name);
        return "start";
    }

    @GetMapping
    public String main(Model model) {
        model.addAttribute("main", "Roquefort cheese");
        return "main";
    }
}
