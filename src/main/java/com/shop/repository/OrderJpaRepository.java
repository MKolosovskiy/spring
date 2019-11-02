package com.shop.repository;

import com.shop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    Optional<Order> getOrderById(Long id);
    Optional<Order> getOrderByIdAndConfirmIsTrue(Long id);
}




