package ru.kata.spring.boot_security.demo.service;


import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {


    @Transactional(readOnly = true)
    List<Role> findAll();

    Role getByName(String roleUser);

    @Transactional
    void add(Role role);

    @Transactional(readOnly = true)
    Role getById(int id);
}
