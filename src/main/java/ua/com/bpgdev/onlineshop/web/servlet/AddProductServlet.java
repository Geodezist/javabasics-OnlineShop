package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AddProductServlet extends HttpServlet {
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("addproduct.ftl");

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        Product product = new Product(request.getParameter("productName"),
                request.getParameter("price"),
                request.getParameter("picturePath"));
        productService.add(product);

        HashMap<String, Object> params = new HashMap<>();
        params.put("operationSuccess", "Product " + product.getName() + " has been added successfully");

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("addproduct.ftl", params);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);

    }

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }
}
