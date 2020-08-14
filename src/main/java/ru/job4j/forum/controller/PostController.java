package ru.job4j.forum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.ThemeService;
import ru.job4j.forum.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/theme/{id}/post")
public class PostController {

    private final PostService postService;

    private final ThemeService themeService;

    private final CommentService commentService;

    private final UserService userService;

    public PostController(PostService postService,
                          ThemeService themeService,
                          CommentService commentService,
                          UserService userService) {
        this.postService = postService;
        this.themeService = themeService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public String getByTheme(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("themeId", id);
        model.addAttribute("posts", postService.findByTheme(Theme.idStub(id)));
        return "posts";
    }

    @GetMapping("/create")
    public String loadCreatingPage(
            @RequestParam(name = "id", required = false) Integer id,
            @PathVariable(name = "id") Integer themeId,
            Model model) {
        Post post = id == null ? new Post() : postService.findById(id).get();
        model.addAttribute("post", post);
        model.addAttribute("themeId", themeId);
        return "edit-post";
    }

    @PostMapping("/create")
    public String savePost(
            @PathVariable(name = "id") Integer themeId,
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String desc,
            Authentication authentication) {
        org.springframework.security.core.userdetails.User u =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user = userService.findByUsername(u.getUsername()).get();
        Theme theme = themeService.findById(themeId).orElseThrow();
        Post post = new Post();
        post.setAuthor(user);
        post.setId(id);
        post.setName(name);
        post.setDescription(desc);
        post.setTheme(theme);
        post.setCreated(LocalDateTime.now());
        postService.saveOrUpdate(post);
        return "redirect:/theme/" + themeId + "/post";
    }

    @GetMapping("/delete")
    public String deleteTheme(@RequestParam("id") Integer id) {
        postService.deleteById(id);
        return "posts";
    }

    @GetMapping("/{postId}")
    public String loadPostPage(
            @PathVariable("id") Integer themeId,
            @PathVariable("postId") Integer postId,
            Model model) {
        Post post = postService.findById(postId).get();
        post.setTheme(themeService.findById(themeId).orElseThrow());
        post.setComments(commentService.findByPost(post));
        model.addAttribute("post", post);
        return "post";
    }

}
