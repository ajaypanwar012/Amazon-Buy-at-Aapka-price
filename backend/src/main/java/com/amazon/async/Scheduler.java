package com.amazon.async;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazon.dto.OrderProductDto;
import com.amazon.models.*;
import com.amazon.service.BuyAtPriceRequestService;
import com.amazon.service.OrderService;
import com.amazon.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    BuyAtPriceRequestService buyAtPriceRequestService;
    ProductService productService;
    OrderService orderService;

    public Scheduler(BuyAtPriceRequestService buyAtPriceRequestService, ProductService productService,
                     OrderService orderService) {
        this.buyAtPriceRequestService = buyAtPriceRequestService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Scheduled(fixedRate = 60000)
    public void runJobEveryMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);
        for (BuyAtPriceRequest buyAtPriceRequest : buyAtPriceRequestService.getAllActiveBuyAtPriceRequests()) {
            Product product = productService.getProduct(buyAtPriceRequest.getProduct().getId());
            if (product.getPrice() <= buyAtPriceRequest.getPrice()) {
                OrderProductDto dto = new OrderProductDto(product, buyAtPriceRequest.getQuantity());
                List<OrderProductDto> dtos = new ArrayList<>();
                dtos.add(dto);
                Order order = this.orderService.create(dtos);
                System.out.println("Automatic Order was placed ");
                // mark buyAtPriceRequest aas Completed so that it get automatically removed and wont get process in
                // next iteration
                buyAtPriceRequest.setStatus(BuyAtPriceRequestStatus.COMPLETED.name());
                buyAtPriceRequestService.update(buyAtPriceRequest);
                // TODO Add a way to notify user/frontend
            }
        }
    }
}