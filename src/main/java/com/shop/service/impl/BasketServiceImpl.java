package com.shop.service.impl;

import com.shop.model.Basket;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.repository.BasketJpaRepository;
import com.shop.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BasketServiceImpl implements BasketService {

    private final BasketJpaRepository basketJpaRepository;

    @Autowired
    public BasketServiceImpl(BasketJpaRepository basketJpaRepository) {
        this.basketJpaRepository = basketJpaRepository;
    }

    @Override
    public void add(Basket basket) {
        basketJpaRepository.save(basket);
    }

    @Override
    public Optional<Basket> getByUser(User user) {
        List<Basket> baskets = basketJpaRepository.getAllByUser(user);
        if (baskets.size() > 0){
            return Optional.ofNullable(baskets.get(baskets.size() - 1));
        }
        return Optional.empty();
    }

    @Override
    public void remove(Basket basket) {
        basketJpaRepository.delete(basket);
    }

    @Override
    public void update(Basket basket) {
        basketJpaRepository.save(basket);
    }
}
