package ru.job4j.forum.model;

import ru.job4j.forum.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Post {

    private int id;

    private String name = "";

    private String desc = "";

    private LocalDateTime created;

    private User author;

    private Theme theme;

    private List<Comment> comments = new ArrayList<>();

    public static Post of(String name) {
        Post post = new Post();
        post.name = name;
        return post;
    }

    public String formattedDateTime() {
        return DateUtils.parse(created);
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(name, post.name)
                && Objects.equals(desc, post.desc)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", desc='"
                + desc
                + '\''
                + ", created="
                + created
                + ", author="
                + author.getUserName()
                + ", theme="
                + theme.getName()
                + '}';
    }
}
