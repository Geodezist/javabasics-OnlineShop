package ua.com.bpgdev.onlineshop.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariConfig HIKARI_CONFIG = new HikariConfig("src/main/resources/property/datasource.properties");
    private static final HikariDataSource HIKARI_DATA_SOURCE = new HikariDataSource( HIKARI_CONFIG );

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return HIKARI_DATA_SOURCE.getConnection();
    }
}
