package com.shop.service.impl;

import com.shop.model.User;
import com.shop.repository.UserJpaRepository;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRepository.saveAndFlush(user);
    }

    @Override
    public void edit(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public void remove(User user) {
        userJpaRepository.delete(user);
    }

    @Override
    public Optional<User> getById(Long id) {
       return userJpaRepository.getUserById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userJpaRepository.getUserByEmail(email);
    }

    @Override
    public void getLines(User user, String repeatPassword, Model model) {
        getLines(user, model);
        model.addAttribute("repeatPassword", repeatPassword);
    }

    @Override
    public void getLines(User user, Model model) {
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", user.getRole());
    }
}
