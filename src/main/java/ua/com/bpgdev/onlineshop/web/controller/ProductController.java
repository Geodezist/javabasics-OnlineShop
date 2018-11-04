package ua.com.bpgdev.onlineshop.web.controller;

import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAll() {
        List<Product> products = productService.getAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("products.ftl", params);
    }

    @ResponseBody
    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public String getProduct(@PathVariable int id){
        Product product = productService.get(id);
        HashMap<String, Object> params = new HashMap<>();
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("product.ftl", params);
    }


    @ResponseBody
    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String addProductPage() {
        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("addproduct.ftl");
    }

    @ResponseBody
    @RequestMapping(
            path = "/product/add",
            method = RequestMethod.POST)
    public String addProduct(@RequestParam String productName, @RequestParam String price, @RequestParam String picturePath) {
        Product product = new Product(productName, price, picturePath);
        productService.add(product);

        HashMap<String, Object> params = new HashMap<>();
        params.put("operationSuccess", "Product " + productName + " has been added successfully");

        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("addproduct.ftl", params);
    }

    @ResponseBody
    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.GET)
    public String updateProduct(@PathVariable int id) {
        Product product = productService.get(id);
        HashMap<String, Object> params = new HashMap<>();
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("updateproduct.ftl", params);
    }

    @ResponseBody
    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable int id, @RequestParam String productName,
                                @RequestParam double price, @RequestParam String picturePath) {
        Product product = productService.get(id);
        product.setName(productName);
        product.setPrice(price);
        product.setPicturePath(picturePath);
        productService.update(product);

        HashMap<String, Object> params = new HashMap<>();
        params.put("operationSuccess", "Product " + product.getName() + " has been updated successfully");
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("updateproduct.ftl", params);
    }

    @RequestMapping(path="/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int id){
        productService.delete(id);
        return "redirect:/products";
    }
}