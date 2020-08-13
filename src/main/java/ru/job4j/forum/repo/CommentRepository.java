package ru.job4j.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPost(Post post);
    void deleteByPost(Post post);

}
