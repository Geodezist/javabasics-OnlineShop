package ua.com.bpgdev.onlineshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.bpgdev.onlineshop.util.AssetLoader;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

//@Controller
public class AssetController {

    //@RequestMapping(path = "/assets/**", method = RequestMethod.GET)
    public void getAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        ServletOutputStream outputStream = response.getOutputStream();

        AssetLoader assetLoader = new AssetLoader();
        InputStream inputStream = assetLoader.loadFile(requestURI);
        inputStream.transferTo(outputStream);
        inputStream.close();
    }
}
