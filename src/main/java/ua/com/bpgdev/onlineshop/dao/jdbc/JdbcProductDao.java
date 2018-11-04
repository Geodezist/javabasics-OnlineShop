package ua.com.bpgdev.onlineshop.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.onlineshop.dao.ProductDao;
import ua.com.bpgdev.onlineshop.dao.jdbc.mapper.ProductRowMapper;
import ua.com.bpgdev.onlineshop.entity.Product;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository("productDao")
public class JdbcProductDao implements ProductDao{
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String SQL_GET_ALL_PRODUCTS =
            "SELECT id, name, picture_path, price, add_date FROM product;";
    private static final String SQL_GET_PRODUCT_BY_ID =
            "SELECT id, name, picture_path, price, add_date FROM product Where id = ?;";
    private static final String SQL_ADD_PRODUCT =
            "INSERT INTO product (name, picture_path, price) VALUES (?,?,?);";
    private static final String SQL_ADD_PRODUCT_WITH_ID =
            "INSERT INTO product (id, name, picture_path, price) VALUES (?,?,?,?);";
    private static final String SQL_UPDATE_PRODUCT =
            "UPDATE product SET name = ?, picture_path = ?, price = ? WHERE id = ?;";
    private static final String SQL_DELETE_PRODUCT =
            "DELETE FROM product WHERE id = ?;";

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao() {
    }
    @PostConstruct
    public void init(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_PRODUCTS, PRODUCT_ROW_MAPPER);
    }

    @Override
    public Product get(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_BY_ID, new Object[] {id}, PRODUCT_ROW_MAPPER);
    }

    @Override
    public void add(Product product) {
        jdbcTemplate.update(SQL_ADD_PRODUCT, product.getName(), product.getPicturePath(), product.getPrice());
    }

    void addWithID(Product product){
        jdbcTemplate.update(SQL_ADD_PRODUCT_WITH_ID, product.getId(), product.getName(), product.getPicturePath(), product.getPrice());
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(SQL_UPDATE_PRODUCT, product.getName(), product.getPicturePath(), product.getPrice(),product.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_PRODUCT, id);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
