package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String loadPage(Model model, @RequestParam(required = false, name = "error") String error) {
        if (error != null) {
            model.addAttribute("errorMsg", error);
        }
        return "login";
    }

    @PostMapping
    public String login(
            @RequestParam String username, @RequestParam String password) {
        Optional<User> userDB = userService.findByUsernameAndPassword(username, password);
        if (userDB.isEmpty()) {
            return "redirect:/login?error=Invalid username or login";
        }
        return "redirect:/";
    }

}
