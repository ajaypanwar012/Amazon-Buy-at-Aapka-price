package com.amazon.controllers;

import com.amazon.dto.OrderProductDto;
import com.amazon.models.Order;
import com.amazon.models.Wallet;
import com.amazon.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> list() {
        return new ResponseEntity<>(this.walletService.getWallet(1).getBalance(), HttpStatus.OK);
    }
}