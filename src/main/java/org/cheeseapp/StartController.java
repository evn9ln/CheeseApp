package org.cheeseapp;

import org.cheeseapp.domain.Client;
import org.cheeseapp.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StartController {

    private final ClientRepo clientRepo;

    public StartController(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

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

    @GetMapping("/testDB")
    public String listClients(Model model){
        model.addAttribute("clients", clientRepo.findAll());
        return "testDB";
    }

    @PostMapping("/testDB")
    public String addClient(@RequestParam String name, @RequestParam String surname) {
        Client client = new Client();
        client.setClientSurname(surname);
        client.setClientName(name);
        clientRepo.save(client);
        return "testDB";
    }
  /*  @PostMapping
    public String addClient(@RequestParam String name,@RequestParam String surname,Model model){
        Client client=new Client(name,surname);
        model.addAttribute("clients",client);
        return "testDB";
    } */

}
