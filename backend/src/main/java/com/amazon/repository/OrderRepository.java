package com.amazon.repository;

import com.amazon.models.Order;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}