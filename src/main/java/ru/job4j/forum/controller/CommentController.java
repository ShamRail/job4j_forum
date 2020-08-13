package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/theme/{theme}/post/{post}")
public class CommentController {

    private final CommentService commentService;

    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/create")
    public String createPost(
            @PathVariable Integer theme,
            @PathVariable Integer post,
            @RequestParam String text) {
        Comment comment = new Comment();
        comment.setText(text);
        comment.setPost(postService.findById(post));
        comment.setCreated(LocalDateTime.now());
        commentService.saveOrUpdate(comment);
        return "redirect:/theme/" + theme + "/post/" + post;
    }

}
