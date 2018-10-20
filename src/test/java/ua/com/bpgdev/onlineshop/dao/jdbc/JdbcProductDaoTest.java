package ua.com.bpgdev.onlineshop.dao.jdbc;

import org.junit.Test;
import ua.com.bpgdev.onlineshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcProductDaoTest {
    private JdbcProductDao jdbcProductDao;

    public JdbcProductDaoTest() throws SQLException {
        jdbcProductDao = new JdbcProductDao(DataSource.getConnection());
    }

    @Test
    public void testGetAll() {

        List<Product> actualProducts = jdbcProductDao.getAll();

        assertNotEquals(0, actualProducts.size());

        for (Product product : actualProducts) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
        }

    }

    @Test
    public void testGet() {
        Product actualProduct = jdbcProductDao.get(1);

        assertEquals(1,actualProduct.getId());

    }

    @Test
    public void testAdd() {
        Product product = new Product();
        product.setName("AAA1");
        product.setPicturePath("BBB");
        product.setPrice(124.5);

        jdbcProductDao.add(product);

    }
}