package ua.com.bpgdev.onlineshop.dao.jdbc;

import org.junit.Test;
import org.springframework.core.SpringProperties;
import ua.com.bpgdev.onlineshop.dao.jdbc.datasource.HikariPostgresDataSource;
import ua.com.bpgdev.onlineshop.dao.jdbc.datasource.SpringPostgresDataSource;
import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class JdbcUserDaoTest {
    private JdbcUserDao jdbcUserDao;

    public JdbcUserDaoTest() throws IOException {
        SpringPostgresDataSource springPostgresDataSource = new SpringPostgresDataSource();
        jdbcUserDao = new JdbcUserDao(springPostgresDataSource.getDataSource());
        jdbcUserDao.init();
    }

    @Test
    public void testGet() {
        User user = jdbcUserDao.getUser("admin");
        assertEquals(UserRole.ADMIN, user.getUserRole());

        user = jdbcUserDao.getUser("guest");
        assertEquals("guest", user.getLogin());

        user = jdbcUserDao.getUser("user");
        assertEquals(2, user.getId());
    }

    @Test
    public void testAdd() {
        /*String[] newLogins = {"admin", "user", "guest"};
        for (String newLogin : newLogins) {
            jdbcUserDao.add(newLogin, newLogin, UserRole.valueOf(newLogin.toUpperCase()));
        }*/
    }
}