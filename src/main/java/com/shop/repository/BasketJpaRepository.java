package com.shop.repository;

import com.shop.model.Basket;
import com.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BasketJpaRepository extends JpaRepository<Basket, Long> {
    List<Basket> getAllByUser(User user);
}
