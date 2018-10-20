package ua.com.bpgdev.onlineshop.dao.jdbc.mapper;

import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {

    public User mapRow(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPasswordHash(resultSet.getString("password_hash"));
        user.setSalt(resultSet.getString("salt"));
        user.setIterations(resultSet.getInt("iterations"));
        user.setUserRole(UserRole.valueOf(resultSet.getString("role")));

        return user;
    }
}
