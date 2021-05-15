package org.cheeseapp.repos;

import org.cheeseapp.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findByName(String name);
    Optional<Product> findById(Integer id);
}
