package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.ftl");

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String userName = request.getParameter("inputUserName");
        String userPassword = request.getParameter("inputPassword");
        Session session = securityService.auth(userName, userPassword);

        if (session != null) {
            Cookie cookie = new Cookie("user-session", session.getToken());
            cookie.setMaxAge(SecurityService.getSessionCookieMaxAge());
            response.addCookie(cookie);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }

    }

    public LoginServlet(SecurityService securityService) {
        this.securityService = securityService;
    }
}
