package ua.com.bpgdev.onlineshop.web.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;
import ua.com.bpgdev.onlineshop.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {
    private static final String USER_SESSION_COOKIE_NAME = "user-session";

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "/ui/user/login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestParam String inputUserName, @RequestParam String inputPassword) {
        Session session = securityService.auth(inputUserName, inputPassword);

        if (session != null) {
            Cookie cookie = new Cookie(USER_SESSION_COOKIE_NAME, session.getToken());
            cookie.setMaxAge(securityService.getSessionCookieMaxAge());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/ui/products";
        } else {
            return "redirect:/ui/login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/ui/login";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerUser() {
        return "/ui/user/registeruser";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam String inputLogin, @RequestParam String inputPassword,
                               @RequestParam String inputCheckPassword) {

        if (inputPassword.equals(inputCheckPassword)) {
            userService.add(inputLogin, inputPassword, UserRole.USER.toString());
            return "redirect:/ui/login";
        } else {
            return "redirect:/ui/registeruser";
        }
    }
}
