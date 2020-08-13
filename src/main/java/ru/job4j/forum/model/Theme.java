package ru.job4j.forum.model;

import ru.job4j.forum.utils.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "themes")
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name = "";

    private String description = "";

    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne
    private User author;

    public static Theme of(String name, String desc) {
        Theme theme = new Theme();
        theme.setName(name);
        theme.setDescription(desc);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
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
