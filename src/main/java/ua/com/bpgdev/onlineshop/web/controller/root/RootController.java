package ua.com.bpgdev.onlineshop.web.controller.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String redirectToUiOnlineShop() {
        return "redirect:/ui/products";
    }
}
