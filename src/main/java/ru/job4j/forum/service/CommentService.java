package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final Map<Integer, Comment> comments = new ConcurrentHashMap<>();

    private final static AtomicInteger IDS = new AtomicInteger();

    public Comment saveOrUpdate(Comment comment) {
        int id = comment.getId() == 0 ? IDS.incrementAndGet() : comment.getId();
        comment.setId(id);
        this.comments.put(id, comment);
        return comment;
    }

    public List<Comment> findByPost(Post post) {
        return comments.values().stream()
                .filter(c -> c.getPost() != null)
                .filter(c -> c.getPost().getId() == post.getId())
                .collect(Collectors.toList());
    }

    public void deleteByPost(Post post) {
        List<Comment> ids = findByPost(post);
        ids.forEach(c -> comments.remove(c.getId()));
    }

}
