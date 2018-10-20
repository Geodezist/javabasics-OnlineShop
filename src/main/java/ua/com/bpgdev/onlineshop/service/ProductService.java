package ua.com.bpgdev.onlineshop.service;

import ua.com.bpgdev.onlineshop.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product get(int id);
    void add(Product product);
    void update(Product product);
    void delete(int id);
}
