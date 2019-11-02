package com.shop.controller;

import com.shop.model.Basket;
import com.shop.model.Order;
import com.shop.model.User;
import com.shop.dto.UserDTO;
import com.shop.service.BasketService;
import com.shop.service.OrderService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    private final UserService userService;
    private final BasketService basketService;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, BasketService basketService,
                          OrderService orderService) {
        this.userService = userService;
        this.basketService = basketService;
        this.orderService = orderService;
    }

    @GetMapping("/add")
    public String add() {
        return "add_user";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute UserDTO userDTO, Model model) {
        User user = new User(userDTO);
        String role = user.getRole().toUpperCase();
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()
                || user.getEmail().isEmpty() || user.getAddress().isEmpty()
                || user.getPassword().isEmpty() || user.getRole().isEmpty()) {
            model.addAttribute("addUserMessage", "An empty field/s");
            userService.getLines(user, model);
            return "add_user";
        } else if (!(role.equals("ROLE_USER") || role.equals("ROLE_ADMIN"))) {
            model.addAttribute("addUserMessage", "The field 'role' should be named as" +
                    " 'ROLE_ADMIN' or 'ROLE_USER'");
            userService.getLines(user, model);
            return "add_user";
        } else {
            userService.add(user);
            return "redirect:/admin/user/all";
        }
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        model.addAttribute("id", id);
        Optional<User> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userService.getLines(user, user.getPassword(), model);
            return "edit_user";
        }
        return "error";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute UserDTO userDTO, @RequestParam Long id,
                       @RequestParam String repeatPassword, Model model) {
        User user = new User(userDTO);
        String role = user.getRole().toUpperCase();
        model.addAttribute("id", id);
        if (user.getFirstName().isEmpty() || user.getLastName().isEmpty()
                || user.getEmail().isEmpty() || user.getAddress().isEmpty()
                || user.getPassword().isEmpty() || user.getRole().isEmpty()) {
            model.addAttribute("editUserMessage", "An empty field/s");
            userService.getLines(user, repeatPassword, model);
            return "edit_user";
        } else if (!(role.equals("ROLE_USER") || role.equals("ROLE_ADMIN"))) {
            model.addAttribute("editUserMessage", "The field 'role' should be named as" +
                    " 'ROLE_ADMIN' or 'ROLE_USER'");
            userService.getLines(user, repeatPassword, model);
            return "edit_user";
        } else if (user.getPassword().equals(repeatPassword)) {
            userService.edit(user);
            return "redirect:/admin/user/all";
        } else {
            model.addAttribute("editUserMessage", "These passwords are different");
            userService.getLines(user, repeatPassword, model);
            return "edit_user";
        }
    }

    @GetMapping("/all")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam Long id) {
        Optional<User> optionalUser = userService.getById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Optional<Order> optionalOrder = orderService.getById(user.getId());
            if (optionalOrder.isPresent()){
               Order order = optionalOrder.get();
               orderService.remove(order);
            }
            Optional<Basket> optionalBasket = basketService.getByUser(user);
            if (optionalBasket.isPresent()) {
                Basket basket = optionalBasket.get();
                basketService.remove(basket);
            }
            userService.remove(user);
            return "redirect:/admin/user/all";
        }
        return "error";
    }
}
