package com.example.oders.repository;

import com.example.oders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value="SELECT * FROM `order` WHERE id=:id", nativeQuery = true)
    Order getOrderById(Integer id);
}
