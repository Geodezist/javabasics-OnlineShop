package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteProductServlet extends HttpServlet {
    private final static Pattern PATTERN = Pattern.compile("^\\/([0-9]+)$");
    private final ProductService productService;


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        String productID = "";
        try {
            Matcher matcher = PATTERN.matcher(request.getPathInfo());
            while (matcher.find()) {
                productID = matcher.group(1);
            }
        } catch (RuntimeException e) {
            System.err.println("Product with id = " + productID + " does not exist in DB or link is invalid.");
        }
        productService.delete(Integer.parseInt(productID));

        response.sendRedirect("/products");
    }

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }
}
