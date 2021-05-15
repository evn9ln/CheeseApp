package org.cheeseapp.repos;

import org.cheeseapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByLogin(String login);

}
