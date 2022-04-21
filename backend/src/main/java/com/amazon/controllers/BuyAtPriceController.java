package com.amazon.controllers;

import com.amazon.dto.BuyAtPriceRequestDto;
import com.amazon.dto.OrderProductDto;
import com.amazon.models.*;
import com.amazon.service.BuyAtPriceRequestService;
import com.amazon.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/buyatprice")
public class BuyAtPriceController {

    BuyAtPriceRequestService buyAtPriceRequestService;
    ProductService productService;

    public BuyAtPriceController(BuyAtPriceRequestService buyAtPriceRequestService, ProductService productService) {
        this.buyAtPriceRequestService = buyAtPriceRequestService;
        this.productService = productService;
    }
    @PostMapping
    public ResponseEntity<BuyAtPriceRequest> create(@RequestBody BuyAtPriceRequestDto dto) {
        BuyAtPriceRequest buyAtPriceRequest = new BuyAtPriceRequest(productService.getProduct(dto
                .getProductId()), dto.getQuantity(), dto.getPrice(), BuyAtPriceRequestStatus.ACTIVE.name());
        buyAtPriceRequest = this.buyAtPriceRequestService.create(buyAtPriceRequest);
        return new ResponseEntity<>(buyAtPriceRequest, HttpStatus.CREATED);
    }
}