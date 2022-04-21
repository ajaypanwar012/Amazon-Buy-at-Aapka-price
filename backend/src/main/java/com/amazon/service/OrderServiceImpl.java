package com.amazon.service;

import com.amazon.dto.OrderProductDto;
import com.amazon.exception.ResourceNotFoundException;
import com.amazon.models.Order;
import com.amazon.models.OrderProduct;
import com.amazon.models.OrderStatus;
import com.amazon.models.Wallet;
import com.amazon.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private OrderProductService orderProductService;
    private WalletService walletService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService,
                            OrderProductService orderProductService, WalletService walletService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderProductService = orderProductService;
        this.walletService = walletService;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(List<OrderProductDto> orderProductDto) {
        validateProductsExistence(orderProductDto);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order.setDateCreated(LocalDate.now());

        order = this.orderRepository.save(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : orderProductDto) {
            orderProducts.add(this.orderProductService.create(new OrderProduct(order, this.productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }
        double totalAmount = order.calculateTotalOrderAmount(orderProducts);
        order.setOrderProducts(orderProducts);
        order.setTotalAmount(totalAmount);
        this.orderRepository.save(order);
        // Hard coded wallet ID is 1 assuming only one customer scenario
        Wallet wallet = walletService.getWallet(1);
        wallet.setBalance((wallet.getBalance() - totalAmount));
        walletService.save(wallet);
        return order;
    }

    private void validateProductsExistence(List<OrderProductDto> orderProductDto) {
        List<OrderProductDto> list = orderProductDto
                .stream()
                .filter(op -> Objects.isNull(this.productService.getProduct(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}