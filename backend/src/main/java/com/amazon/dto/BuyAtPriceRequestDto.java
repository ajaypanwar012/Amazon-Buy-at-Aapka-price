package com.amazon.dto;

import com.amazon.models.BuyAtPriceRequest;

import javax.validation.constraints.NotNull;

public class BuyAtPriceRequestDto {

    private Long id;
    private @NotNull Long productId;
    private double price;
    private Integer quantity;

    public BuyAtPriceRequestDto(@NotNull Long productId, double price, Integer quantity) {
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
    }

    public BuyAtPriceRequestDto(BuyAtPriceRequest buyAtPriceRequest) {
        this.setId(buyAtPriceRequest.getId());
        this.setProductId(buyAtPriceRequest.getProduct().getId());
        this.setPrice(buyAtPriceRequest.getPrice());
    }

    public BuyAtPriceRequestDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}