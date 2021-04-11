package org.cheeseapp.controllers;

import org.cheeseapp.domain.Client;
import org.cheeseapp.domain.Product;
import org.cheeseapp.repos.ClientRepo;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
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

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private SetRepo setRepo;

    @GetMapping("/products")
    public String listClients(Model model){
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String addClient(@RequestParam String name, @RequestParam Integer cost, Model model) {
        Product product = new Product(name, cost);
        productRepo.save(product);
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
