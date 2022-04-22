package com.amazon.service;

import com.amazon.models.BuyAtPriceRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface BuyAtPriceRequestService {

    @NotNull Iterable<BuyAtPriceRequest> getAllActiveBuyAtPriceRequests();

    BuyAtPriceRequest create(@NotNull(message = "The buy at price cannot be null.") @Valid BuyAtPriceRequest buyAtPriceRequest);

    void update(@NotNull(message = "The buy at price cannot be null.") @Valid BuyAtPriceRequest buyAtPriceRequest);
}


