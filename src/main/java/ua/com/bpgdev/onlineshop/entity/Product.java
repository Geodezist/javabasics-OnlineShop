package ua.com.bpgdev.onlineshop.entity;

import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private String picturePath;
    private double price;
    private LocalDateTime addDate;

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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public Product(String name, String price, String picturePath) {
        this.name = name;
        this.picturePath = picturePath;
        this.price = Double.valueOf(price);
    }

    public Product() {
    }
}
