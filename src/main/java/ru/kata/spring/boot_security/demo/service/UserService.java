package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void addUser(User user, String roleAdmin, String roleUser);

    void updateUser(User user);

    User getUserId(long id);

    void updateUser(User user, String roleAdmin, String roleUser);

    User removeUser(long id);

    User getByName(String username);

    void addUser(User user);
}
