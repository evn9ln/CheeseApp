package org.cheeseapp.controllers;

import org.cheeseapp.domain.*;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
import org.cheeseapp.repos.UserRepo;
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepo.findByLogin(login);
        Iterable<Order> ordersFromDb=orderRepo.findAllByUserId(user);


        model2.addAttribute("orders",ordersFromDb);
        model.addAttribute("user", user);
        Collection<Role> roles=user.getRoles();
        if(roles.size()>1)
            return "profileAdmin";
        return "profile";
    }

    @PostMapping("/changeprof")
    public String changeProf(User user, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User userFromDb = userRepo.findByLogin(login);
        userFromDb.set(user);
        userRepo.save(userFromDb);

        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String login2 = auth.getName();
        User userFromDb2 = userRepo.findByLogin(login2);
        model.addAttribute("user", userFromDb2);
        return "redirect:/profile";
    }

    @GetMapping("/profileAdmin")
    public String profileAdmin(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepo.findByLogin(login);

        model.addAttribute("user", user);

        return "profileAdmin";
    }
    @GetMapping("/statisticPage")
    public String statisticPage(Model model){
        Iterable<Product> allProducts=productRepo.findAll();
        Collection<ProductStatistic> allProductStatistic=new ArrayList<ProductStatistic>() ;
        for(Product product:allProducts){
            int count=0;
            Iterable<Set> allSetsWithProductName=setRepo.findAllByProductId(product);
            for(Set set:allSetsWithProductName){
                count+=set.getNumber();

            }
            ProductStatistic productStatistic=new ProductStatistic(product.getId(),
                    product.getName(),count,product.getPrice()*count);
            allProductStatistic.add(productStatistic);
        }
        model.addAttribute("productStatistic",allProductStatistic);
        return "productStatistic";


    }

    @GetMapping("/orderPage")
    public String orderPage(Model model) {
        Iterable<Order> allOrders=orderRepo.findAll();
        ArrayList<OrderInfo> allOrdersInfo=new ArrayList<OrderInfo>();
        for(Order order: allOrders){
            String clientName=order.getUserId().getName();
            String phoneNumber=order.getUserId().getPhone();
            Date date=order.getDate();
            Integer orderSum=order.getOrderSum();
            Iterable<Set> setsForOrder=setRepo.findAllByOrderId(order);
            ArrayList<ProductStatistic> productList=new ArrayList<>();
            int counter=0;
            for(Set set: setsForOrder){
                counter++;
                ProductStatistic productStatistic=new ProductStatistic(counter,set.getProductId().getName(),
                        set.getNumber(),set.getNumber()*set.getProductId().getPrice());
                productList.add(productStatistic);
            }
            OrderInfo orderInfo=new OrderInfo(clientName,phoneNumber,date,orderSum,productList);
            allOrdersInfo.add(orderInfo);
        }
        model.addAttribute("ordersInfo",allOrdersInfo);

        return "orderInfo";
    }

}
