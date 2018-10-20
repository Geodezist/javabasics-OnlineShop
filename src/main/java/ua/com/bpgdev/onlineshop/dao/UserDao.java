package ua.com.bpgdev.onlineshop.dao;

import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

public interface UserDao {
    User getUser(String login);

    void add(String login, String password, UserRole role);
}
