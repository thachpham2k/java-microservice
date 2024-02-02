package com.microservice.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.orderservice.model.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}
