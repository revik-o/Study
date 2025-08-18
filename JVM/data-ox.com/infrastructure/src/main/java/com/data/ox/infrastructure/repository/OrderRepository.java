package com.data.ox.infrastructure.repository;

import com.data.ox.infrastructure.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    // Everything has already been implemented
}
