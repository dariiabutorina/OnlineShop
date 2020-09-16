package com.internet.shop.model;

import java.util.Set;

public class User {
    private final String login;
    private String password;
    private String name;
    private Long id;
    private Set<Role> roles;

    public User(String name, String login,
                String password, Set<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
