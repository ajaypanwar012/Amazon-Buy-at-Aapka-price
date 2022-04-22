package com.amazon.service;

import com.amazon.exception.ResourceNotFoundException;
import com.amazon.models.Wallet;
import com.amazon.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet getWallet(long id) {
        return walletRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
    }

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet addMoneyToWallet(long id , double money){
        Wallet wallet = getWallet(id);
        wallet.setBalance(wallet.getBalance() + money);
        return save(wallet);
    }
}