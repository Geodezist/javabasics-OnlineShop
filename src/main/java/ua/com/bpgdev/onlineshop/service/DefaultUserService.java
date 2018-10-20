package ua.com.bpgdev.onlineshop.service;

import ua.com.bpgdev.onlineshop.dao.UserDao;
import ua.com.bpgdev.onlineshop.entity.User;

public class DefaultUserService implements UserService {
    private final UserDao userDao;

    @Override
    public User getUser(String userName) {
        return userDao.getUser(userName);
    }

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }
}