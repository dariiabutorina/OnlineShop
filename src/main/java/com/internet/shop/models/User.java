package com.internet.shop.models;

public class User {
    private final String login;
    private String password;
    private String name;
    private Long id;

    public User(String name,
                String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User { "
                + "id = " + id
                + " , name = " + name
                + " , login = " + login
                + " , password = " + password
                + " }";
    }
}
