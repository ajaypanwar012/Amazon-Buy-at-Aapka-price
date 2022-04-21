package com.amazon.controllers;

import com.amazon.models.Order;
import com.amazon.models.Wallet;
import com.amazon.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull double list() {
        return this.walletService.getWallet(1).getBalance();
    }
}