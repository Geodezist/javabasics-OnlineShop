package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AllProductsServlet extends HttpServlet {
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws  IOException {

        List<Product> products = productService.getAll();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("products.ftl", params);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    public AllProductsServlet(ProductService productService) {
        this.productService = productService;
    }
}
