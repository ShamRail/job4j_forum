package ru.job4j.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Theme;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByTheme(Theme theme);
}
