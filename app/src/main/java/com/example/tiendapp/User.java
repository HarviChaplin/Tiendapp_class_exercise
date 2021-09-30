package com.example.tiendapp;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String name, email, urlFoto, password;

    public User() {
    }

    public User(String name, String email, String urlFoto, String password) {
        this.name = name;
        this.email = email;
        this.urlFoto = urlFoto;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
