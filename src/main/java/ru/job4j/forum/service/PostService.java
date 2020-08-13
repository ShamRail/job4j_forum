package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Theme;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final static AtomicInteger IDS = new AtomicInteger();

    private final CommentService commentService;

    public PostService(CommentService commentService) {
        this.commentService = commentService;
    }

    public Post saveOrUpdate(Post post) {
        int id = post.getId() == 0 ? IDS.incrementAndGet() : post.getId();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public boolean deleteById(int id) {
        Post post = posts.get(id);
        if (post == null) {
            return false;
        }
        commentService.deleteByPost(post);
        return posts.remove(id) != null;
    }

    public void deleteByTheme(Theme theme) {
        Collection<Post> ids = findByTheme(theme);
        ids.forEach(p -> deleteById(p.getId()));
    }

    public Collection<Post> findByTheme(Theme theme) {
        return posts.values().stream()
                .filter(p -> p.getTheme() != null)
                .filter(p -> p.getTheme().getId() == theme.getId())
                .collect(Collectors.toList());
    }

    public Post findById(Integer id) {
        return posts.get(id);
    }
}
