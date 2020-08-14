package ru.job4j.forum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ThemeService;
import ru.job4j.forum.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/theme")
public class ThemeController {

    private final ThemeService themeService;

    private final UserService userService;

    public ThemeController(ThemeService themeService, UserService userService) {
        this.themeService = themeService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String loadPage(Model model, @RequestParam(required = false) Integer id) {
        Theme theme = id == null ? new Theme() : themeService.findById(id).orElse(new Theme());
        model.addAttribute(theme);
        return "edit-theme";
    }

    @PostMapping("/create")
    public String createTheme(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String desc,
            Authentication authentication) {
        org.springframework.security.core.userdetails.User u =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user = userService.findByUsername(u.getUsername()).get();
        Theme theme = new Theme();
        theme.setAuthor(user);
        theme.setName(name);
        theme.setDescription(desc);
        theme.setId(id);
        theme.setCreated(LocalDateTime.now());
        themeService.saveOrUpdate(theme);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTheme(@RequestParam Integer id) {
        themeService.deleteById(id);
        return "redirect:/";
    }

}
