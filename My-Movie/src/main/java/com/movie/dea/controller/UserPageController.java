package com.movie.dea.controller;

import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserPageController {
    private final UserService userService;

    public UserPageController(UserService userService){this.userService = userService;}


    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "users/new";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("userForm") UserForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return form.getUser_id() == null ? "users/html" : "users/edit";
        }

        User user;

        if (form.getUser_id() == null) {
            user = new User();
        } else {
            user = userService.getUserById(form.getUser_id());
        }


        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setPhone_number(form.getPhone_number());
        user.setAge(form.getAge());

        userService.add(user);
        return "redirect;/users";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/list";
    }

    @GetMapping("/{user_id}/edit")
    public String edit(@PathVariable Integer user_id, Model model) {
        User user = userService.getUserById(user_id);
        UserForm form = new UserForm();

        form.setUser_id(user.getUser_id());
        form.setUsername(user.getUsername());
        form.setPassword(user.getPassword());
        form.setPhone_number(user.getPhone_number());
        form.setAge(user.getAge());

        model.addAttribute("userForm", form);
        return "users/edit";
    }
}
