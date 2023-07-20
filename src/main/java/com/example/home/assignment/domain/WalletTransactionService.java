package com.example.home.assignment.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class that enriches wallet transaction entities with additional information.
 */
@Service
public class WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    @Autowired
    public WalletTransactionService(WalletTransactionRepository walletTransactionRepository) {
        this.walletTransactionRepository = walletTransactionRepository;
    }

    /**
     * Enriches the given {@link WalletTransaction} and saves to repository.
     *
     * @param entity           The {@link WalletTransaction} to be enriched.
     * @param baseCurrencyRate The base currency rate used for calculation.
     */
    @Transactional
    public void enrichAndSave(WalletTransaction entity, Double baseCurrencyRate) {
        walletTransactionRepository.save(entity.calculateBaseCurrencyAmount(baseCurrencyRate));
    }
}
