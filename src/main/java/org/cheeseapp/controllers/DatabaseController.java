package org.cheeseapp.controllers;

import org.cheeseapp.domain.*;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
import org.cheeseapp.repos.UserRepo;
import org.cheeseapp.services.OrderService;
import org.cheeseapp.services.ProductService;
import org.cheeseapp.services.SetService;
import org.cheeseapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.CollectionTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class DatabaseController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private SetRepo setRepo;

    @GetMapping("/products")
    public String listProducts(Model model) {
        model.addAttribute("products", productRepo.findAll());
        User user = UserService.getCurrentUser(userRepo);
        if (UserService.getCountRoles(user) > 1)
            return "productsAdmin";
        return "products";
    }

    @PostMapping("/products")
    public String addProduct(@RequestParam String name, @RequestParam(required = false)
            Integer price, @RequestParam String description, @RequestParam String imgLink, Model model, Model modelWrongName) {
        if (!ProductService.addProduct(name, price, description, imgLink, productRepo)) {
            modelWrongName.addAttribute("message", "error");
        }
        model.addAttribute("products", productRepo.findAll());
        return "productsAdmin";
    }

    @PostMapping("/change")
    public String changeProduct(Product product, Model model) {
        ProductService.changeProduct(product, productRepo);
        model.addAttribute("products", productRepo.findAll());
        return "redirect:/products";
    }

    @PostMapping("/createSet")
    public String createSet(@RequestParam String productName, @RequestParam(required = false) Integer number,
                            Model model, Model modelWrongName) {

        if (!SetService.createSet(productName, number, orderRepo, setRepo, userRepo, productRepo)) {
            modelWrongName.addAttribute("message", "wrong name or number");

        }

        model.addAttribute("products", productRepo.findAll());


        return "products";
    }

    @GetMapping("/cart")
    public String orderList(Model model, Model model2) {

        model.addAttribute("products", OrderService.showCart(userRepo, orderRepo, setRepo));
        model2.addAttribute("orderSum", OrderService.getOrderSumCart(orderRepo, userRepo));

        return "cart";
    }

    @GetMapping("/orderStatus")
    public String orderStatus(Model model) {
        model.addAttribute("user", UserService.getCurrentUser(userRepo));
        return "redirect:/profile";

    }

}
