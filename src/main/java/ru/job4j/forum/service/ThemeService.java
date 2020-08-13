package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.repo.ThemeRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Optional;

@Service
public class ThemeService {

    private final PostService postService;

    private final ThemeRepository themeRepository;

    public ThemeService(PostService postService, ThemeRepository themeRepository) {
        this.postService = postService;
        this.themeRepository = themeRepository;
    }

    //@PostConstruct
    private void initData() {
        saveOrUpdate(Theme.of("Stream API", "Обсуждаем стрим апи"));
        saveOrUpdate(Theme.of("Collections", "Обсуждаем коллекции"));
        saveOrUpdate(Theme.of("JDBC", "Обсуждаем работу с БД"));
        saveOrUpdate(Theme.of("Hibernate", "Обсуждаем Hibernate"));
        saveOrUpdate(Theme.of("Spring", "Обсуждаем Spring"));
    }

    public Theme saveOrUpdate(Theme theme) {
        return themeRepository.save(theme);
    }

    public Collection<Theme> findAll() {
        return themeRepository.findAll();
    }

    public Optional<Theme> findById(int id) {
        return themeRepository.findById(id);
    }

    public void deleteById(int id) {
        postService.deleteByTheme(Theme.idStub(id));
        themeRepository.deleteById(id);
    }

}
