package com.amazon.repository;

import com.amazon.models.OrderProduct;
import com.amazon.models.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}