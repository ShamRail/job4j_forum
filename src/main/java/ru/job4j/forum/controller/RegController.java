package ru.job4j.forum.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repo.AuthorityRepository;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

@Controller
public class RegController {

    private final UserService userService;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public RegController(UserService userService, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/reg")
    public String loadPage(Model model,
            @RequestParam(value = "error", required = false, name = "error") String error) {
        if (error != null) {
            model.addAttribute("errorMsg", error);
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String saveUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        Optional<User> userDB = userService.findByEmailOrUsername(email, username);
        if (userDB.isPresent()) {
            return "redirect:/reg?error=User with nickname or email already exists";
        }
        Authority authority = authorityRepository.findByAuthority("ROLE_USER");
        User user = new User();
        user.setAuthority(authority);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userService.save(user);
        return "redirect:/login";
    }

}
