package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.security.entity.UserRole;
import ua.com.bpgdev.onlineshop.service.UserService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserServlet extends HttpServlet {
    private UserService userService;

    public RegisterUserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("registeruser.ftl");

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String login = request.getParameter("inputLogin");
        String password = request.getParameter("inputPassword");
        String checkPassword = request.getParameter("inputCheckPassword");
        if (password.equals(checkPassword)){
            userService.add(login, password, UserRole.USER.toString());
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("/register");
        }
    }
}
