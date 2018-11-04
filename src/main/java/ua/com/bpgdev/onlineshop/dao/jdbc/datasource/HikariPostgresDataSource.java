package ua.com.bpgdev.onlineshop.dao.jdbc.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class HikariPostgresDataSource {

    private static final HikariConfig HIKARI_CONFIG = new HikariConfig("/property/datasource.properties");
    private static final HikariDataSource HIKARI_DATA_SOURCE = new HikariDataSource(HIKARI_CONFIG);

    private HikariPostgresDataSource() {
    }

    public DataSource getDataSource() {
        return HIKARI_DATA_SOURCE;
    }

    public static Connection getConnection() throws SQLException {
        return HIKARI_DATA_SOURCE.getConnection();
    }
}
