package org.cheeseapp.controllers;

import org.cheeseapp.domain.Client;
import org.cheeseapp.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DatabaseController {
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/testDB")
    public String listClients(Model model){
        Iterable<Client> clients = clientRepo.findAll();
        model.addAttribute("clients", clients);
        return "testDB";
    }

    @PostMapping("/testDB")
    public String addClient(@RequestParam String name, @RequestParam String surname, Model model) {
        Client client = new Client(name, surname);
        clientRepo.save(client);
        Iterable<Client> clients = clientRepo.findAll();
        model.addAttribute("clients", clients);
        return "testDB";
    }
}
