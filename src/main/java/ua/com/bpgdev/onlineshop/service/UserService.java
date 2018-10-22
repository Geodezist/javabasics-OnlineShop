package ua.com.bpgdev.onlineshop.service;

import ua.com.bpgdev.onlineshop.entity.User;

public interface UserService {
    User getUser(String userName);
    void add(String login, String password, String role);
}
