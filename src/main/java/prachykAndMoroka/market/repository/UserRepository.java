package prachykAndMoroka.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prachykAndMoroka.market.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByName(String name);

}
