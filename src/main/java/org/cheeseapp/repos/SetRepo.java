package org.cheeseapp.repos;

import org.cheeseapp.domain.Order;
import org.cheeseapp.domain.Product;
import org.cheeseapp.domain.Set;
import org.springframework.data.repository.CrudRepository;

public interface SetRepo extends CrudRepository<Set, Integer> {

    Iterable<Set> findAllByOrderId(Order orderId);

    Set findByOrderIdAndProductId(Order orderId, Product productId);

    Iterable<Set> findAllByProductId(Product productId);
}
