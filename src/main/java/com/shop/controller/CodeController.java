package com.shop.controller;

import com.shop.model.Basket;
import com.shop.model.Order;
import com.shop.model.User;
import com.shop.service.BasketService;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/user/code/send")
public class CodeController {

    private final OrderService orderService;
    private final BasketService basketService;

    @Autowired
    public CodeController(OrderService orderService, BasketService basketService) {
        this.orderService = orderService;
        this.basketService = basketService;
    }

    @GetMapping
    public String send() {
        return "send_code";
    }

    @PostMapping
    public String send(@AuthenticationPrincipal User user, @RequestParam String sendCode){
        Optional<Order> optionalOrder = orderService.getById(user.getId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            if (sendCode.equals(order.getCode())) {
                basketService.add(new Basket(user, new ArrayList<>()));
                orderService.confirm(order);
                return "redirect:/user/product/all";
            } else {
                return "redirect:/user/code/send";
            }
        }
        return "error";
    }
}
