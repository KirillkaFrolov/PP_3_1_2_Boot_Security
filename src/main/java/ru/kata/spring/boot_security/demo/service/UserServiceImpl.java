package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserDao userDao;
    private RoleService roleService;


    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user, String roleAdmin, String roleUser) {

    }

    @Override
    public User getUserId(long id) {
        return userDao.getUserId(id);
    }

    @Override
    @Transactional
    public void updateUser(User user, String roleAdmin, String roleUser) {

    }

    @Override
    @Transactional
    public void addUser(User user) {
        Set<Role> roles = new HashSet<>();

        if (user.getRoles().contains("ROLE_ADMIN")) {
            roles.add(roleService.getByName("ROLE_ADMIN"));
        }
        if (user.getRoles().contains("ROLE_USER")) {
            roles.add(roleService.getByName("ROLE_USER"));
        }
        if (roles.isEmpty()) {
            roles.add(roleService.getByName("ROLE_USER"));
        }

        user.setRoles(roles);
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Set<Role> roles = new HashSet<>();

        if (user.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getName()))) {
            roles.add(roleService.getByName("ROLE_ADMIN"));
        }

        if (user.getRoles().stream().anyMatch(role -> "ROLE_USER".equals(role.getName()))) {
            roles.add(roleService.getByName("ROLE_USER"));
        }

        if (roles.isEmpty()) {
            roles.add(roleService.getByName("ROLE_USER"));
        }

        user.setRoles(roles);

        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public User removeUser(long id) {
        return userDao.removeUser(id);
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByName(username);
        return user;
    }
}