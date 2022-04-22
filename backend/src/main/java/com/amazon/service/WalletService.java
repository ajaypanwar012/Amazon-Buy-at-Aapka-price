package com.amazon.service;

import com.amazon.models.Wallet;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
public interface WalletService {
    Wallet getWallet(@Min(value = 1L, message = "Invalid Wallet ID.") long id);

    Wallet save(Wallet wallet);

    Wallet addMoneyToWallet(long id , double money);

}
