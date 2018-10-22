package ua.com.bpgdev.onlineshop;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.com.bpgdev.onlineshop.dao.*;
import ua.com.bpgdev.onlineshop.dao.jdbc.*;
import ua.com.bpgdev.onlineshop.security.*;
import ua.com.bpgdev.onlineshop.service.*;
import ua.com.bpgdev.onlineshop.web.filter.*;
import ua.com.bpgdev.onlineshop.web.servlet.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Starter {
    public static void main(String[] args) throws Exception {
        // DAO config
        ProductDao jdbcProductDao = new JdbcProductDao(DataSource.getConnection());
        UserDao jdbcUserDao = new JdbcUserDao(DataSource.getConnection());

        // Service config
        ProductService defaultProductService = new DefaultProductService(jdbcProductDao);
        UserService defaultUserService = new DefaultUserService(jdbcUserDao);
        SecurityService securityService = new SecurityService(defaultUserService);

        // Servlets config
        //    - Assets
        AssetsServlet assetsServlet = new AssetsServlet();

        //    - Login/Logout
        LoginServlet loginServlet = new LoginServlet(securityService);
        LogoutServlet logoutServlet = new LogoutServlet();
        RegisterUserServlet registerUserServlet = new RegisterUserServlet(defaultUserService);

        //    - Products
        AllProductsServlet allProductsServlet = new AllProductsServlet(defaultProductService);
        GetProductServlet getProductServlet = new GetProductServlet(defaultProductService);
        AddProductServlet addProductServlet = new AddProductServlet(defaultProductService);
        UpdateProductServlet updateProductServlet = new UpdateProductServlet(defaultProductService);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(defaultProductService);


        // Server config
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //    - Servlets holders
        context.addServlet(new ServletHolder(allProductsServlet), "/");
        context.addServlet(new ServletHolder(assetsServlet), "/assets/*");

        context.addServlet(new ServletHolder(loginServlet), "/login");
        context.addServlet(new ServletHolder(logoutServlet), "/logout");
        context.addServlet(new ServletHolder(registerUserServlet), "/register");

        context.addServlet(new ServletHolder(allProductsServlet), "/products");
        context.addServlet(new ServletHolder(getProductServlet), "/product/*");
        context.addServlet(new ServletHolder(addProductServlet), "/product/add");
        context.addServlet(new ServletHolder(updateProductServlet), "/product/edit/*");
        context.addServlet(new ServletHolder(deleteProductServlet), "/product/delete/*");

        //    - Filters
        AdminSecurityFilter adminSecurityFilter = new AdminSecurityFilter(securityService);
        context.addFilter(new FilterHolder(adminSecurityFilter), "/product/*", EnumSet.of(DispatcherType.REQUEST));
        UserSecurityFilter userSecurityFilter = new UserSecurityFilter(securityService);
        context.addFilter(new FilterHolder(userSecurityFilter), "/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
