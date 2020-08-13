package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Theme;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThemeService {

    private final Map<Integer, Theme> themes = new ConcurrentHashMap<>();

    private final static AtomicInteger IDS = new AtomicInteger();

    private final PostService postService;

    public ThemeService(PostService postService) {
        this.postService = postService;
    }

    @PostConstruct
    private void initData() {
        saveOrUpdate(Theme.of("Stream API", "Обсуждаем стрим апи"));
        saveOrUpdate(Theme.of("Collections", "Обсуждаем коллекции"));
        saveOrUpdate(Theme.of("JDBC", "Обсуждаем работу с БД"));
        saveOrUpdate(Theme.of("Hibernate", "Обсуждаем Hibernate"));
        saveOrUpdate(Theme.of("Spring", "Обсуждаем Spring"));
    }

    public Theme saveOrUpdate(Theme theme) {
        int id = theme.getId() == 0 ? IDS.incrementAndGet() : theme.getId();
        theme.setId(id);
        themes.put(theme.getId(), theme);
        return theme;
    }

    public Collection<Theme> findAll() {
        return themes.values();
    }

    public Optional<Theme> findById(int id) {
        return Optional.ofNullable(themes.get(id));
    }

    public boolean deleteById(int id) {
        postService.deleteByTheme(Theme.idStub(id));
        return themes.remove(id) != null;
    }

}
