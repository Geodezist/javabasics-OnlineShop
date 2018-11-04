package ua.com.bpgdev.onlineshop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;
import ua.com.bpgdev.onlineshop.service.UserService;
import ua.com.bpgdev.onlineshop.web.templater.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
public class SecurityController {
private static final String USER_SESSION_COOKIE_NAME = "user-session";

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;


    @ResponseBody
    @RequestMapping(path="/login", method = RequestMethod.GET)
    public String login(){
        PageGenerator pageGenerator = PageGenerator.instance();
        return pageGenerator.getPage("login.ftl");
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestParam String inputUserName, @RequestParam String inputPassword){
        Session session = securityService.auth(inputUserName, inputPassword);

        if (session != null) {
            Cookie cookie = new Cookie(USER_SESSION_COOKIE_NAME, session.getToken());
            cookie.setMaxAge(SecurityService.getSessionCookieMaxAge());
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/products";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(path="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/login";
    }

    @ResponseBody
    @RequestMapping(path="/register", method = RequestMethod.GET)
    public String registerUser(){
        PageGenerator pageGenerator = PageGenerator.instance();
        return  pageGenerator.getPage("registeruser.ftl");

    }

    @RequestMapping(path="/register", method = RequestMethod.POST)
    public String registerUser(@RequestParam String inputLogin, @RequestParam String  inputPassword,
                               @RequestParam String inputCheckPassword){

        if (inputPassword.equals(inputCheckPassword)){
            userService.add(inputLogin, inputPassword, UserRole.USER.toString());
            return "redirect:/login";
        } else {
            return "redirect:/register";
        }
    }
}
