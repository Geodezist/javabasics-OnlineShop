package ua.com.bpgdev.onlineshop.web.controller.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;

import java.util.List;

@RestController
public class RestProductController {
    private ProductService productService;

    public RestProductController(@Autowired ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) {
        return productService.get(id);
    }

    @RequestMapping(path = "/product", method = RequestMethod.POST)
    public String add(@RequestBody Product product) {

        return product.toString();
    }

}
