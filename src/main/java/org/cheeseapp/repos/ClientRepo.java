package org.cheeseapp.repos;

import org.cheeseapp.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepo extends CrudRepository<Client, Integer> {
}
