package ru.job4j.forum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/theme/{theme}/post/{post}")
public class CommentController {

    private final CommentService commentService;

    private final PostService postService;

    private final UserService userService;

    public CommentController(CommentService commentService, PostService postService, UserService userService) {
        this.commentService = commentService;
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createPost(
            @PathVariable Integer theme,
            @PathVariable Integer post,
            @RequestParam String text,
            Authentication authentication) {
        org.springframework.security.core.userdetails.User u =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user = userService.findByUsername(u.getUsername()).get();
        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setMessage(text);
        comment.setPost(postService.findById(post).get());
        comment.setCreated(LocalDateTime.now());
        commentService.saveOrUpdate(comment);
        return "redirect:/theme/" + theme + "/post/" + post;
    }

}
