package ua.com.bpgdev.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.onlineshop.dao.UserDao;
import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.PasswordHashFactory;
import ua.com.bpgdev.onlineshop.security.entity.PasswordEntity;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service("userService")
public class DefaultUserService implements UserService {
    @Autowired
    private UserDao userDao;

    public DefaultUserService() {
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(String userName) {
        return userDao.getUser(userName);
    }

    @Override
    public void add(String login, String password, String role) {
        try  {
            PasswordEntity passwordEntity = PasswordHashFactory.generatePasswordEntity(password);
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPasswordHash(passwordEntity.getPasswordHash());
            newUser.setSalt(passwordEntity.getSalt());
            newUser.setIterations(passwordEntity.getIterations());
            newUser.setUserRole(UserRole.valueOf(role));

            userDao.add(newUser);

        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException(e);
        }
    }

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }
}