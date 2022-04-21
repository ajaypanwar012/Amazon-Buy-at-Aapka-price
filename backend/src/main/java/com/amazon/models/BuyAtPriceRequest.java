package com.amazon.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "buyatpricerequests")
public class BuyAtPriceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    private double price;
    private String status;

    public BuyAtPriceRequest() {
    }

    public BuyAtPriceRequest(Product product, Integer quantity, double price, String status) {
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
