package com.shop.controller;

import com.shop.model.Basket;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.BasketService;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/user/basket")
public class BasketController {

    private final BasketService basketService;
    private final ProductService productService;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping
    public String basket(@AuthenticationPrincipal User user, Model model) {
        Optional<Basket> optionalBasket = basketService.getByUser(user);
        if (optionalBasket.isPresent()) {
            Basket basket = optionalBasket.get();
            model.addAttribute("products", basket.getProducts());
            model.addAttribute("size", basket.getProducts().size());
            return "basket";
        }
        return "error";
    }

    @GetMapping("/product/remove")
    public String remove(@AuthenticationPrincipal User user, @RequestParam Long id, Model model) {
        Optional<Basket> optionalBasket = basketService.getByUser(user);
        Optional<Product> optionalProduct = productService.getById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (optionalBasket.isPresent()) {
                Basket basket = optionalBasket.get();
                basket.removeProduct(product);
                basketService.update(basket);
                model.addAttribute("products", basket.getProducts());
                model.addAttribute("size", basket.getProducts().size());
                return "basket";
            }
        }
        return "error";
    }
}
