package ua.com.bpgdev.onlineshop.dao.jdbc;

import ua.com.bpgdev.onlineshop.dao.ProductDao;
import ua.com.bpgdev.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import ua.com.bpgdev.onlineshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    private final Connection connection;
    private final static ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    public JdbcProductDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> getAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, picture_path, price, add_date FROM product;");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product get(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT id, name, picture_path, price, add_date FROM product Where id = ?;")
        ) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                resultSet.next();
                return PRODUCT_ROW_MAPPER.mapRow(resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO product (name, picture_path, price) VALUES (?,?,?);")
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPicturePath());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE product SET name = ?, picture_path = ?, price = ? WHERE id = ?;")
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getPicturePath());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM product WHERE id = ?;")
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
