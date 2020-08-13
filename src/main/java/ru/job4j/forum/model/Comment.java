package ru.job4j.forum.model;

import ru.job4j.forum.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {

    private int id;

    private String text;

    private LocalDateTime created;

    private Post post;

    private User author;

    public String formattedDateTime() {
        return DateUtils.parse(created);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return id == comment.id && Objects.equals(created, comment.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }

    @Override
    public String toString() {
        return "Comment{"
                + "id="
                + id
                + ", text='"
                + text
                + '\''
                + ", created="
                + created
                + ", post="
                + post.getName()
                + ", author="
                + author.getUserName()
                + '}';
    }
}
