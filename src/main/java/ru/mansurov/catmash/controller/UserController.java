package ru.mansurov.catmash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.mansurov.catmash.model.User;
import ru.mansurov.catmash.model.service.UserServiceImpl;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("registrationForm") User user) {

        boolean userCreation;
        userCreation = userServiceImpl.addUser(user);

        if (userCreation) {
            return "redirect:/login?successRegistration";
        }
        return "redirect:/registration?error";

    }
}
