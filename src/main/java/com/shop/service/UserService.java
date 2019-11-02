package com.shop.service;

import com.shop.model.User;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    void edit(User user);
    void remove(User user);
    List<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByEmail(String email);
    void getLines(User user, Model model);
    void getLines(User user, String repeatPassword, Model model);
}
