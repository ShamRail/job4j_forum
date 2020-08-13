package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.repo.PostRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {

    private final CommentService commentService;

    private final PostRepository postRepository;

    public PostService(CommentService commentService, PostRepository postRepository) {
        this.commentService = commentService;
        this.postRepository = postRepository;
    }

    public Post saveOrUpdate(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(int id) {
        commentService.deleteByPost(Post.idStub(id));
        postRepository.deleteById(id);
    }

    public void deleteByTheme(Theme theme) {
        Collection<Post> byTheme = findByTheme(theme);
        byTheme.forEach(commentService::deleteByPost);
        postRepository.deleteAll(byTheme);
    }

    public Collection<Post> findByTheme(Theme theme) {
        return postRepository.findByTheme(theme);
    }

    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }
}
