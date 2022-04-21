package com.amazon.dto;

import com.amazon.models.Product;

public class OrderProductDto {

    private Product product;
    private Integer quantity;

    public OrderProductDto(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}