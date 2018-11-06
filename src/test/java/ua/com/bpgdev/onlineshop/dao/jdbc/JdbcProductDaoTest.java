package ua.com.bpgdev.onlineshop.dao.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.bpgdev.onlineshop.dao.jdbc.datasource.SpringPostgresDataSource;
import ua.com.bpgdev.onlineshop.entity.Product;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class JdbcProductDaoTest {
    private JdbcProductDao jdbcProductDao;

    public JdbcProductDaoTest() throws IOException {
        SpringPostgresDataSource springPostgresDataSource = new SpringPostgresDataSource();
        jdbcProductDao = new JdbcProductDao(springPostgresDataSource.getDataSource());
        jdbcProductDao.init();
    }

    @Before
    public void beforeEachTest() {
        Product product = new Product();
        product.setId(1);
        product.setName("AAA");
        product.setPicturePath("BBB");
        product.setPrice(222.2);

        jdbcProductDao.addWithId(product);
    }

    @After
    public void afterEachTest() {
        jdbcProductDao.delete(1);
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

        assertEquals(1, actualProduct.getId());
    }

    @Test
    public void testAdd() {
        int previousSize = jdbcProductDao.getAll().size();

        Product product = new Product();
        product.setName(UUID.randomUUID().toString());
        product.setPicturePath(UUID.randomUUID().toString());
        product.setPrice(123.45);
        jdbcProductDao.add(product);

        int currentSize = jdbcProductDao.getAll().size();

        assertEquals(previousSize + 1, currentSize);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete() {
        jdbcProductDao.delete(1);
        jdbcProductDao.get(1);
    }

    @Test
    public void testUpdate() {
        Product product = jdbcProductDao.get(1);
        assertEquals("AAA", product.getName());
        product.setName("CCC");
        jdbcProductDao.update(product);
        assertEquals("CCC", jdbcProductDao.get(1).getName());
    }
}