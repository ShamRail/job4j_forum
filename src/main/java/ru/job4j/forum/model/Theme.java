package ru.job4j.forum.model;

import ru.job4j.forum.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Theme {

    private int id;

    private String name = "";

    private String desc = "";

    private LocalDateTime created;

    private User author;

    public static Theme of(String name, String desc) {
        Theme theme = new Theme();
        theme.setName(name);
        theme.setDesc(desc);
        theme.setCreated(LocalDateTime.now());
        return theme;
    }

    public static Theme idStub(int id) {
        Theme theme = new Theme();
        theme.setId(id);
        return theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String formattedDateTime() {
        return DateUtils.parse(created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Theme theme = (Theme) o;
        return id == theme.id
                && Objects.equals(name, theme.name)
                && Objects.equals(created, theme.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created);
    }
}
