package ua.com.bpgdev.onlineshop.dao.jdbc.datasource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import ua.com.bpgdev.onlineshop.util.InputStreamFromFileFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SpringPostgresDataSource {

    @Bean
    public DataSource getDataSource() throws IOException {
        Yaml yaml = new Yaml();
        DataSourceConfiguration configuration;
        try (InputStream inputStream = new InputStreamFromFileFactory().getInputStreamFromFile("/property/datasource.yml")) {
            configuration = yaml.loadAs(inputStream, DataSourceConfiguration.class);
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(configuration.getDriverClassName());
        dataSource.setUrl(configuration.getUrl());
        dataSource.setUsername(configuration.getUsername());
        dataSource.setPassword(configuration.getPassword());

        return dataSource;
    }
}
