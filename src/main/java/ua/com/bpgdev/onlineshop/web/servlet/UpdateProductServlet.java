package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;
import ua.com.bpgdev.onlineshop.web.util.IdParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class UpdateProductServlet extends HttpServlet {
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        Product product = null;
        try {
            int id = IdParser.getId(request.getPathInfo());
            product = productService.get(id);
        } catch (RuntimeException e) {
            System.err.println("Product does not exist in DB or link is invalid.");
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("updateproduct.ftl", params);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        Product product = null;
        try {
            int id = IdParser.getId(request.getPathInfo());
            product = productService.get(id);
        } catch (RuntimeException e) {
            System.err.println("Product does not exist in DB or link is invalid.");
        }
        product.setName(request.getParameter("productName"));
        product.setPrice(Double.valueOf(request.getParameter("price")));
        product.setPicturePath(request.getParameter("picturePath"));
        productService.update(product);

        HashMap<String, Object> params = new HashMap<>();
        params.put("operationSuccess", "Product " + product.getName() + " has been updated successfully");
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("updateproduct.ftl", params);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    public UpdateProductServlet(ProductService defaultProductService) {
        this.productService = defaultProductService;
    }
}
