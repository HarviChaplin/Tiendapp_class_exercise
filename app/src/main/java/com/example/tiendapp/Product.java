package com.example.tiendapp;

import java.io.Serializable;

public class Product implements Serializable {

    private int id;
    private String name, detail, urlImage;
    private Double price;

    public Product() {
    }

    public Product(String name, String urlImage, Double price) {
        this.name = name;
        this.urlImage = urlImage;
        this.price = price;
        this.detail = "no description";
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String description) {
        this.detail = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + " ($" + price + ") ";
    }
}
