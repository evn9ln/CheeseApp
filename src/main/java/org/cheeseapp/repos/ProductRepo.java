package org.cheeseapp.repos;

import org.cheeseapp.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findByName(String name);
}
