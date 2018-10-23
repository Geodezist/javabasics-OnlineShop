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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetProductServlet extends HttpServlet {
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        Product product = null;
        try {
            product = productService.get(IdParser.getId(request.getPathInfo()));
        } catch (RuntimeException e) {
            System.err.println("Product with id = " + request.getPathInfo() + " does not exist in DB or link is invalid.");
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("product", product);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("product.ftl", params);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    public GetProductServlet(ProductService productService) {
        this.productService = productService;
    }
}
