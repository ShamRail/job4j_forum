package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final static AtomicInteger IDS = new AtomicInteger(0);

    public User save(User user) {
        user.setId(IDS.incrementAndGet());
        users.put(user.getId(), user);
        return user;
    }

    public Optional<User> findByEmailOrUsername(String email, String userName) {
        return users.values().stream()
                .filter(u -> u.getUsername().equals(userName) || u.getEmail().equals(email))
                .findAny();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.values().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findAny();
    }
}
