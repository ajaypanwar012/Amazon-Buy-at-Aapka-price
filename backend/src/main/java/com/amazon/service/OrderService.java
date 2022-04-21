package com.amazon.service;

import com.amazon.dto.OrderProductDto;
import com.amazon.models.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid List<OrderProductDto> formDtos);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}