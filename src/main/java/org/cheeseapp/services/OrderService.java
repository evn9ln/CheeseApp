package org.cheeseapp.services;

import org.cheeseapp.domain.*;
import org.cheeseapp.repos.OrderRepo;
import org.cheeseapp.repos.SetRepo;
import org.cheeseapp.repos.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    public static ArrayList<OrderInfo> getAllOrdersInfo(OrderRepo orderRepo, SetRepo setRepo) {
        Iterable<Order> allOrders = orderRepo.findAll();
        ArrayList<OrderInfo> allOrdersInfo = new ArrayList<OrderInfo>();
        for (Order order : allOrders) {
            String clientName = order.getUserId().getName();
            String phoneNumber = order.getUserId().getPhone();
            Date date = order.getDate();
            Integer orderSum = order.getOrderSum();
            Boolean status = order.getStatus();
            Iterable<Set> setsForOrder = setRepo.findAllByOrderId(order);
            ArrayList<ProductStatistic> productList = new ArrayList<>();

            for (Set set : setsForOrder) {

                ProductStatistic productStatistic = new ProductStatistic(set.getProductId().getName(),
                        set.getNumber(), set.getNumber() * set.getProductId().getPrice());
                productList.add(productStatistic);
            }
            OrderInfo orderInfo = new OrderInfo(clientName, phoneNumber, date, orderSum, productList, status);
            allOrdersInfo.add(orderInfo);
        }
        return allOrdersInfo;
    }

    public static Iterable<ProductStatistic> showCart(UserRepo userRepo, OrderRepo orderRepo,
                                                      SetRepo setRepo) {

        User user = UserService.getCurrentUser(userRepo);
        Order orderFromDb = orderRepo.findByUserIdAndStatus(user, false);
        Iterable<Set> sets = setRepo.findAllByOrderId(orderFromDb);
        List<ProductStatistic> products = new ArrayList<>();
        for (Set set : sets) {
            ProductStatistic productStatistic = new ProductStatistic(set.getProductId().getName()
                    , set.getNumber(), set.getProductId().getPrice());
            products.add(productStatistic);
        }
        return products;


    }

    public static Integer getOrderSumCart(OrderRepo orderRepo, UserRepo userRepo) {
        User user = UserService.getCurrentUser(userRepo);
        return orderRepo.findByUserIdAndStatus(user, false).getOrderSum();
    }

    public static void changeOrderStatus(OrderRepo orderRepo, Integer orderId, String status) {
        Order order = orderRepo.findById(orderId).get();
        if (order == null) {
        } else {
            if (status.equals("accept")) {
                order.setStatus(false);
            } else if (status.equals("complete")) {
                order.setStatus(true);
            }
            orderRepo.save(order);
        }
    }
}
