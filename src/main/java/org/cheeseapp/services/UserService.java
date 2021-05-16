package org.cheeseapp.services;

import org.cheeseapp.domain.Role;
import org.cheeseapp.domain.User;
import org.cheeseapp.repos.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    public static User getCurrentUser(UserRepo userRepo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        return userRepo.findByLogin(login);
    }

    public static Integer getCountRoles(User user) {
        return user.getRoles().size();
    }

    public static Boolean addUser(User user, UserRepo userRepo) {
        User userFromDb = userRepo.findByLogin(user.getLogin());
        if (userFromDb != null) {
            return false;
        }
        user.setRoles(Collections.singleton(Role.CLIENT)); //set with one value (here client)
        user.setActive(true);
        userRepo.save(user);
        return true;
    }
}
