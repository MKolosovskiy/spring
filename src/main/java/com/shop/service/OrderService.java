package com.shop.service;

import com.shop.model.Order;
import java.util.Optional;

public interface OrderService {
    void add(Order order);
    Optional<Order> getById(Long id);
    void remove (Order order);
    void confirm(Order order);
   Optional<Order> getOrderByIdAndConfirmIsTrue(Long id);
}