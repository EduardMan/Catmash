package ru.mansurov.catmash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mansurov.catmash.model.service.MashServiceImpl;

@Controller
public class HomePageController {

    @Autowired
    MashServiceImpl mashService;

    @GetMapping
    public String homePage(Model model) {

        model.addAttribute("mashs", mashService.findAll());
        return "mainPage";
    }
}
