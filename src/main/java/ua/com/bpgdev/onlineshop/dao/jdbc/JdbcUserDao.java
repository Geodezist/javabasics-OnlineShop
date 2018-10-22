package ua.com.bpgdev.onlineshop.dao.jdbc;

import ua.com.bpgdev.onlineshop.dao.UserDao;
import ua.com.bpgdev.onlineshop.dao.jdbc.mapper.UserRowMapper;
import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.PasswordHashFactory;
import ua.com.bpgdev.onlineshop.security.entity.PasswordEntity;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDao implements UserDao {
    private static final String GET_USER_BY_LOGIN_SQL =
            "SELECT id, login, password_hash, salt, iterations, role FROM \"user\" WHERE UPPER(login) = UPPER(?);";
    private static final String ADD_USER =
            "INSERT INTO \"user\" (login, password_hash, salt, iterations, role) VALUES (?, ?, ?, ?, ?);";

    private final static UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private final Connection connection;


    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUser(String login) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_SQL)) {

            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return USER_ROW_MAPPER.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void add(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPasswordHash());
            preparedStatement.setString(3, user.getSalt());
            preparedStatement.setInt(4, user.getIterations());
            preparedStatement.setString(5, user.getUserRole().toString());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
