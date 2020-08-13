package ru.job4j.forum.model;

import java.util.Objects;

public class User {

    private int id;

    private String userName;

    private String password;

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && Objects.equals(userName, user.userName)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email);
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", userName='"
                + userName
                + '\''
                + ", password='"
                + password
                + '\''
                + ", email='"
                + email
                + '\''
                + '}';
    }
}
