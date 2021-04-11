package org.cheeseapp.controllers;

import org.cheeseapp.domain.Product;
import org.cheeseapp.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DatabaseController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/products")
    public String listProducts(Model model){
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@RequestParam String name, @RequestParam Integer price, Model model) {
        Product product = new Product(name, price);
        productRepo.save(product);
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
