package ua.com.bpgdev.onlineshop.web.filter;

import ua.com.bpgdev.onlineshop.security.SecurityService;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.security.entity.UserRole;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserSecurityFilter implements Filter{
    private final SecurityService securityService;

    public UserSecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String pathInfo = httpServletRequest.getServletPath();
        if(pathInfo.equals("/login") || pathInfo.startsWith("/assets")){
            chain.doFilter(request, response);
            return;
        }

        boolean isAuth = false;

        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-session")) {
                    String userToken = cookie.getValue();
                    Session session = securityService.getSession(userToken);
                    if (session == null){
                        break;
                    }
                    UserRole userRole = session.getUser().getUserRole();
                    if ( userRole == UserRole.USER || userRole == UserRole.ADMIN) {
                        isAuth = true;
                    }
                    break;
                }
            }
        }

        if (isAuth) {
            chain.doFilter(request, response);
        } else {
            httpServletResponse.sendRedirect("/login");
        }

    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
