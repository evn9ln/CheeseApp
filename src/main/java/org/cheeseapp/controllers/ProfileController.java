package org.cheeseapp.controllers;

import org.cheeseapp.domain.*;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
import org.cheeseapp.repos.UserRepo;
import org.cheeseapp.services.OrderService;
import org.cheeseapp.services.ProductService;
import org.cheeseapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SetRepo setRepo;

    @GetMapping("/profile")
    public String profile(Model model, Model model2) {
        User user = UserService.getCurrentUser(userRepo);
        model2.addAttribute("orders", orderRepo.findAllByUserId(user));
        model.addAttribute("user", user);
        if (user.getRoles().size() > 1)
            return "profileAdmin";
        return "profile";
    }

    @PostMapping("/changeprof")
    public String changeProf(User user, Model model) {
        User userFromDb = UserService.getCurrentUser(userRepo);
        userFromDb.set(user);
        userRepo.save(userFromDb);
        model.addAttribute("user", userFromDb);
        return "redirect:/profile";
    }

//    @GetMapping("/profileAdmin")
//    public String profileAdmin(Model model) {
//        User user = userRepo.findByLogin(UserService.getCurrentUserLogin());
//        model.addAttribute("user", user);
//        return "profileAdmin";
//    }

    @GetMapping("/statisticPage")
    public String statisticPage(Model model) {
        model.addAttribute("productStatistic", ProductService.getProductsStatistic(productRepo, setRepo));
        return "productStatistic";
    }

    @GetMapping("/orderPage")
    public String orderPage(Model model) {
        model.addAttribute("ordersInfo", OrderService.getAllOrdersInfo(orderRepo, setRepo));
        return "orderInfo";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(@RequestParam Integer orderId, @RequestParam(value = "select") String status) {
        OrderService.changeOrderStatus(orderRepo, orderId, status);
        return "redirect:/orderPage";
    }

}
