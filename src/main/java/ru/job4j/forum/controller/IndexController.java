package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.ThemeService;

@Controller
public class IndexController {

    private final ThemeService themeService;

    public IndexController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("themes", themeService.findAll());
        return "index";
    }

}
