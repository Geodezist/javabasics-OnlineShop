package ua.com.bpgdev.onlineshop.web.servlet;

import ua.com.bpgdev.onlineshop.util.AssetLoader;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class AssetsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestURI = req.getRequestURI();
        ServletOutputStream outputStream = resp.getOutputStream();

        AssetLoader assetLoader = new AssetLoader();
        InputStream inputStream = assetLoader.loadFile("/resources" + requestURI);
        inputStream.transferTo(outputStream);
        inputStream.close();

    }
}
