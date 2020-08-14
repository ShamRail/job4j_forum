package ru.job4j.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.forum.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailOrUsername(String email, String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
