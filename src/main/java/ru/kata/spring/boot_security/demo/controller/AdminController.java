package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String usersALL(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users";
    }


    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }


    @PostMapping("/new")
    public String add(User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }


    @GetMapping("/edit/{id}")
    public String updateUser(Model model, @PathVariable("id") long id) {
        model.addAttribute(userService.getUserId(id));
        return "edit";
    }


    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }
}