package ru.mansurov.catmash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.mansurov.catmash.model.service.MashServiceImpl;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomePageController {

    @Autowired
    MashServiceImpl mashService;

    @Value("${pictures.min.count}")
    private int picturesMinCount;

    @Value("${pictures.max.count}")
    private int picturesMaxCount;

    @Value("${mash.min.name.length}")
    private int minMashNameLength;

    @Value("${mash.max.name.length}")
    private int maxMashNameLength;

    @Value("${mash.min.message.length}")
    private int minMashMessage;

    @Value("${mash.max.message.length}")
    private int maxMashMessage;

    @GetMapping
    public String homePage(Model model) {
        // find all mashes and show him for user
        model.addAttribute("mashs", mashService.findAll());

        // Properties of application
        Map<String, Integer> parameters = new HashMap<>();
        parameters.put("picturesMinCount", picturesMinCount);
        parameters.put("picturesMaxCount", picturesMaxCount);
        parameters.put("minMashNameLength", minMashNameLength);
        parameters.put("maxMashNameLength", maxMashNameLength);
        parameters.put("minMashMessage", minMashMessage);
        parameters.put("maxMashMessage", maxMashMessage);
        model.addAttribute("parameters", parameters);

        return "mainPage";
    }
}
