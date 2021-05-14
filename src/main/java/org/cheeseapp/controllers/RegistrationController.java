package org.cheeseapp.controllers;


import org.cheeseapp.domain.Role;
import org.cheeseapp.domain.User;
import org.cheeseapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb=userRepo.findByLogin(user.getLogin());
         if(userFromDb != null){
             model.put("message", "existingUser");
             return "registration";
         }
         user.setRoles(Collections.singleton(Role.CLIENT)); //set with one value (here client)
         user.setActive(true);
         userRepo.save(user);
        return "redirect:/login";
    }

}
