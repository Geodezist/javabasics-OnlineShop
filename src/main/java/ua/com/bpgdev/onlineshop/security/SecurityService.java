package ua.com.bpgdev.onlineshop.security;

import ua.com.bpgdev.onlineshop.entity.User;
import ua.com.bpgdev.onlineshop.security.entity.Session;
import ua.com.bpgdev.onlineshop.service.UserService;

import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class SecurityService {
    private static int sessionCookieMaxAge;
    private List<Session> sessionList = new ArrayList<>();
    private UserService userService;

    public SecurityService(UserService userService) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("src/main/resources/property/security.properties"));
        sessionCookieMaxAge = Integer.valueOf(properties.getProperty("cookie.maxAge"));

        this.userService = userService;
    }

    public Session auth(String login, String password) {
        try {
            User user = userService.getUser(login);
            if (user != null && PasswordHashFactory.validatePassword(password, user.getIterations(),
                    user.getPasswordHash(), user.getSalt())) {

                String token = UUID.randomUUID().toString();
                Session session = new Session();
                session.setUser(user);
                session.setToken(token);
                session.setExpireDate(LocalDateTime.now().plusSeconds(sessionCookieMaxAge));

                sessionList.add(session);
                return session;
            }
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Session getSession(String userToken) {
        for (Session session : sessionList) {
            if (session.getToken().equals(userToken) &&
                    session.getExpireDate().compareTo(LocalDateTime.now()) > 0) {
                return session;
            }
        }
        return null;
    }

    public static int getSessionCookieMaxAge() {
        return sessionCookieMaxAge;
    }
}