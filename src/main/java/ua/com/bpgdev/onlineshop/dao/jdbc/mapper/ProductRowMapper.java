package ua.com.bpgdev.onlineshop.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.onlineshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String picturePath = resultSet.getString("picture_path");
        double price = resultSet.getDouble("price");
        Timestamp addDateTimestamp = resultSet.getTimestamp("add_date");

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPicturePath(picturePath);
        product.setPrice(price);
        product.setAddDate(addDateTimestamp.toLocalDateTime());

        return product;
    }
}
