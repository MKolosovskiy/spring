package com.shop.controller;

import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.annotation.PostConstruct;

@Controller
public class LoginController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public LoginController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        User admin = new User("1", "1", "1@net", "1", "1", "ROLE_ADMIN");
        User first_user = new User("22", "2", "2@net", "2", "2","ROLE_USER");
        User second_user = new User("33", "3", "3@net", "3", "3","ROLE_USER");
        Product product = new Product("wine", "red", 20.0);
        userService.add(first_user);
        userService.add(second_user);
        userService.add(admin);
        productService.add(product);
    }

    @GetMapping("/")
    public String login(@AuthenticationPrincipal User user) {
        if (user == null){
            return "redirect:/login";
        }
        return (user.getRole().equals("ROLE_ADMIN")) ? "redirect:/admin/user/all"
                : "redirect:/user/product/all";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }
}
