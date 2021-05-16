package org.cheeseapp.services;

import org.cheeseapp.domain.Order;
import org.cheeseapp.domain.Product;
import org.cheeseapp.domain.Set;
import org.cheeseapp.domain.User;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.ProductRepo;
import org.cheeseapp.repos.SetRepo;
import org.cheeseapp.repos.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SetService {

    public static Boolean createSet(String productName, Integer number, OrderRepo orderRepo,
                                    SetRepo setRepo, UserRepo userRepo, ProductRepo productRepo) {
        Product productFromDb = productRepo.findByName(productName);
        if (productFromDb == null || number == null) {
            return false;
        }

        User user = UserService.getCurrentUser(userRepo);
        Order orderFromDb = orderRepo.findByUserIdAndStatus(user, false);
        if (orderFromDb == null) {
            Order newOrder = new Order(user);
            orderRepo.save(newOrder);
        }

        Order orderFromDb2 = orderRepo.findByUserIdAndStatus(user, false);
        Set setFromDb = setRepo.findByOrderIdAndProductId(orderFromDb2, productFromDb);
        if (setFromDb == null) {
            Set newSet = new Set(orderFromDb2, productFromDb, number);
            setRepo.save(newSet);
        } else {
            setFromDb.setNumber(number + setFromDb.getNumber());
            setRepo.save(setFromDb);
        }
        orderFromDb2.setOrderSum(productFromDb.getPrice() * number + orderFromDb2.getOrderSum());
        orderRepo.save(orderFromDb2);
        return true;
    }
}
