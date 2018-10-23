package ua.com.bpgdev.onlineshop.security.entity;


import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private String token;
    private User user;
    private LocalDateTime expireDate;
    private List<Product> productList;

    public Session() {
        productList = new ArrayList<>();
    }

    public void addProductToCart(Product product){
        productList.add(product);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public String toString() {
        return "Session{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", productList=" + productList.toString() +
                '}';
    }
}
