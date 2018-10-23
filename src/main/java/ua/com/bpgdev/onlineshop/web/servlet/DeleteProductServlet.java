package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.util.IdParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteProductServlet extends HttpServlet {
    private final ProductService productService;


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            productService.delete(IdParser.getId(request.getPathInfo()));
        } catch (RuntimeException e) {
            System.err.println("Product with id = " + request.getPathInfo() + " does not exist in DB or link is invalid.");
            throw new RuntimeException(e);
        } finally {
            response.sendRedirect("/products");
        }
    }

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }
}
