package ua.com.bpgdev.onlineshop.entity;

import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private String picturePath;
    private double price;
    private LocalDateTime addDate;

    public Product() {
    }

    public Product(String name, String price, String picturePath) {
        this.name = name;
        this.picturePath = picturePath;
        this.price = Double.parseDouble(price);
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", price=" + price +
                ", addDate=" + addDate +
                '}';
    }
}
