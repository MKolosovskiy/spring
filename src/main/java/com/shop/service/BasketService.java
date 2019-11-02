package com.shop.service;

import com.shop.model.Basket;
import com.shop.model.User;
import java.util.Optional;

public interface BasketService {
    void add(Basket basket);
    Optional<Basket> getByUser(User user);
    void remove(Basket basket);
    void update(Basket basket);
}
