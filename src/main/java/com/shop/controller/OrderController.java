package com.shop.controller;

import com.shop.model.Basket;
import com.shop.model.Order;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.BasketService;
import com.shop.service.MailService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class OrderController {

    private final MailService mailService;
    private final OrderService orderService;
    private final BasketService basketService;
    private final ProductService productService;

    @Autowired
    public OrderController(MailService mailService, OrderService orderService,
                           BasketService basketService, ProductService productService) {
        this.mailService = mailService;
        this.orderService = orderService;
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping("/product/all")
    public String buy(@AuthenticationPrincipal User user, Model model) {
        Optional<Basket> optionalBasket = basketService.getByUser(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            model.addAttribute("size", basket.getProducts().size());
        }
        model.addAttribute("products", productService.getAll());
        return "buy_product";
    }

    @GetMapping("/product/buy/{id}")
    public String buy(@PathVariable("id") Long id, @AuthenticationPrincipal User user) {
        Optional<Product> optionalProduct = productService.getById(id);
        Optional<Basket> optionalBasket = basketService.getByUser(user);
        Basket basket = optionalBasket.orElseGet(() -> new Basket(user, new ArrayList<>()));
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            basket.addProduct(product);
            basketService.add(basket);
            return "redirect:/user/product/all";
        }
        return "error";
    }

    @GetMapping("/order")
    public String order(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("address", user.getAddress());
        return "order";
    }

    @PostMapping("/order")
    public String order(@AuthenticationPrincipal User user) {
        String code = CodeGenerator.generate();
        mailService.sendCode(code, user.getEmail());
        Optional<Basket> optionalBasket = basketService.getByUser(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            Order order = new Order(user.getFirstName(), user.getLastName(), user.getAddress(),
                    user.getEmail(), code, basket);
            orderService.add(order);
            return "redirect:/user/code/send";
        }
        return "error";
    }
}
