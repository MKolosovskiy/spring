package com.shop.service;

import com.shop.model.Product;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    void add(Product product);
    void edit(Product product);
    void remove(Product product);
    List<Product> getAll();
    Optional<Product> getById(Long id);
    void getLines(Product product, Model model);
}
