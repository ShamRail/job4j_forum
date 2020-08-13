package ru.job4j.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.forum.model.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
}
