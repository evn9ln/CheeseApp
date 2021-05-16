package org.cheeseapp.repos;

import org.cheeseapp.domain.Order;
import org.cheeseapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
    Order findByUserId(User userId);

    Iterable<Order> findAllByUserIdAndStatus(User userId, Boolean status);

    Order findByUserIdAndStatus(User userId, Boolean status);

    Iterable<Order> findAllByUserId(User userId);
}
