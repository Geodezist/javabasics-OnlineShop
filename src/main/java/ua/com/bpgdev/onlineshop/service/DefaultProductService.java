package ua.com.bpgdev.onlineshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.onlineshop.dao.ProductDao;
import ua.com.bpgdev.onlineshop.entity.Product;

import java.util.List;

@Service("productService")
public class DefaultProductService implements ProductService {
    @Autowired
    private ProductDao productDao;

    public DefaultProductService() {
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public DefaultProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product get(int id) {
        return productDao.get(id);
    }

    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
