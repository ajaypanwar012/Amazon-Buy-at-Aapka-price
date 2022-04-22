package com.amazon.controllers;

import com.amazon.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/wallet")
public class WalletController {

    WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<Double> list() {
        return new ResponseEntity<>(this.walletService.getWallet(1).getBalance(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Double> update(@RequestBody double money) {
        return new ResponseEntity<>(this.walletService.addMoneyToWallet(1, money).getBalance(), HttpStatus.OK);
    }
}