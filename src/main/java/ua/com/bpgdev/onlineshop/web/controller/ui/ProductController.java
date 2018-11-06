package ua.com.bpgdev.onlineshop.web.controller.ui;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Product> products = productService.getAll();

        model.addAttribute("products", products);
        return "/ui/product/products";
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public String getProduct(@PathVariable int id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);

        return "/ui/product/product";
    }


    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String addProductPage() {
        return "/ui/product/addproduct";
    }

    @RequestMapping(
            path = "/product/add",
            method = RequestMethod.POST)
    public String addProduct(@RequestParam String productName,
                             @RequestParam String price,
                             @RequestParam String picturePath,
                             Model model) {
        Product product = new Product(productName, price, picturePath);
        productService.add(product);

        model.addAttribute("operationSuccess",
                "Product " + productName + " has been added successfully");
        return "/ui/product/addproduct";
    }

    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.GET)
    public String updateProduct(@PathVariable int id,
                                Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "/ui/product/updateproduct";
    }

    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.POST)
    public String updateProduct(@PathVariable int id,
                                @RequestParam String productName,
                                @RequestParam double price,
                                @RequestParam String picturePath,
                                Model model) {
        Product product = productService.get(id);
        product.setName(productName);
        product.setPrice(price);
        product.setPicturePath(picturePath);
        productService.update(product);

        model.addAttribute("operationSuccess",
                "Product " + product.getName() + " has been updated successfully");
        model.addAttribute("product", product);
        return "/ui/product/updateproduct";
    }

    @RequestMapping(path = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}