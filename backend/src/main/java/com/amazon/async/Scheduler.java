package com.amazon.async;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazon.models.BuyAtPriceRequest;
import com.amazon.service.BuyAtPriceRequestService;
import com.amazon.service.ProductService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    BuyAtPriceRequestService buyAtPriceRequestService;
    ProductService productService;

    public Scheduler(BuyAtPriceRequestService buyAtPriceRequestService, ProductService productService) {
        this.buyAtPriceRequestService = buyAtPriceRequestService;
        this.productService = productService;
    }

    @Scheduled(fixedRate = 60000)
    public void runJobEveryMinute() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);
        for (BuyAtPriceRequest buyAtPriceRequest : buyAtPriceRequestService.getAllActiveBuyAtPriceRequests()) {
            System.out.println("print ");
//            System.out.println("print " + buyAtPriceRequest.getProduct().getPrice());
//            System.out.println("print " + buyAtPriceRequest.getPrice());
//            if (buyAtPriceRequest.getPrice() <= buyAtPriceRequest.getProduct().getPrice()) {
//                System.out.println("Nachoooooo");
//                System.out.println("print " + buyAtPriceRequest.getQuantity());
//            }
        }
    }
}