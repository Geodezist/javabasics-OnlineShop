package ua.com.bpgdev.onlineshop.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.onlineshop.dao.UserDao;
import ua.com.bpgdev.onlineshop.dao.jdbc.mapper.UserRowMapper;
import ua.com.bpgdev.onlineshop.entity.User;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class JdbcUserDao implements UserDao {
    private static final String SQL_GET_USER_BY_LOGIN =
            "SELECT id, login, password_hash, salt, iterations, role FROM \"user\" WHERE UPPER(login) = UPPER(?);";
    private static final String SQL_ADD_USER =
            "INSERT INTO \"user\" (login, password_hash, salt, iterations, role) VALUES (?, ?, ?, ?, ?);";
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(@Autowired DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(String login) {
        return jdbcTemplate.queryForObject(SQL_GET_USER_BY_LOGIN, USER_ROW_MAPPER, login);
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(SQL_ADD_USER,
                user.getLogin(),
                user.getPasswordHash(),
                user.getSalt(),
                user.getIterations(),
                user.getUserRole().toString());
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
