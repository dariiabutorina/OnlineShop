package com.internet.shop.model;

import java.util.Set;

public class User {
    private String name;
    private final String login;
    private String password;
    private byte[] salt;
    private Long id;
    private Set<Role> roles;

    public User(Long id, String name,
                String login, String password, byte[] salt) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
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
