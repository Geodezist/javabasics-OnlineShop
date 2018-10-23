package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.entity.Product;
import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;
import ua.com.bpgdev.onlineshop.service.ProductService;
import ua.com.bpgdev.onlineshop.web.util.IdParser;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddToCartServlet extends HttpServlet {
    private final static Pattern PATTERN = Pattern.compile("^\\/([0-9]+)$");
    private final ProductService productService;
    private final SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Session session = null;
        Product product = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-session")) {
                    String userToken = cookie.getValue();
                    session = securityService.getSession(userToken);
                    break;
                }
            }
        }

        if (session != null) {
            try {
                product = productService.get(IdParser.getId(request.getPathInfo()));
                //if (session.getProductList().contains(product))

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                response.sendRedirect("/products");
            }
        }
    }

    public AddToCartServlet(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }
}
