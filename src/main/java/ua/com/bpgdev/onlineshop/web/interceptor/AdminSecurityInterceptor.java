package ua.com.bpgdev.onlineshop.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = LoggerFactory.getLogger(AdminSecurityInterceptor.class);
    @Autowired
    private SecurityService securityService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        LOG.info("---------->>>>  START Applying filter");
        boolean isAuth = false;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-session")) {
                    String userToken = cookie.getValue();
                    Session session = securityService.getSession(userToken);
                    if (session != null && session.getUser().getUserRole() == UserRole.ADMIN) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }

        if (!isAuth) {
            response.sendRedirect("/login");
            LOG.info("---------->>>>  STOP Applying filter - DENY!");
            return false;
        }
        LOG.info("---------->>>>  STOP Applying filter - ALLOW!");
        return true;
    }
}
