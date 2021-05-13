package org.cheeseapp.controllers;

import org.cheeseapp.domain.Order;
import org.cheeseapp.domain.Product;
import org.cheeseapp.domain.Set;
import org.cheeseapp.domain.User;
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

import javax.persistence.CollectionTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @PostMapping("/change")
    public String changePrice(@RequestParam String name, @RequestParam Integer price, Model model){
        Product productFromDb=productRepo.findByName(name);
        productFromDb.setPrice(price);
        productRepo.save(productFromDb);
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }
    @PostMapping("/createOrder")
    public String createOrder(@RequestParam String productName,@RequestParam Integer number, Model model){
        Product productFromDb=productRepo.findByName(productName);
        if(productFromDb==null){
            return "products";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        User user = userRepo.findByLogin(login);
        Order orderFromDb=orderRepo.findByUserId(user);
        if(orderFromDb==null){
            Order newOrder=new Order(user);
            orderRepo.save(newOrder);
        }
        Order orderFromDb2=orderRepo.findByUserId(user);
        Set newSet=new Set(orderFromDb2,productFromDb,number);
        setRepo.save(newSet);

        Iterable<Set> sets= setRepo.findAllByOrderId(orderFromDb2);


        List<Product> products= new ArrayList<Product>();
        for(Set set:sets){
            products.add(set.getProductId());
        }
        model.addAttribute("products2", products);



        return "redirect:/products";
    }
}
