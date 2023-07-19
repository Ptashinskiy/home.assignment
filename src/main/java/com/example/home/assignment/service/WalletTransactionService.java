package com.example.home.assignment.service;

import com.example.home.assignment.dao.Repository;
import com.example.home.assignment.domain.WalletTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class that enriches wallet transaction entities with additional information.
 */
@Service
public class WalletTransactionService {

    private final Repository repository;

    @Autowired
    public WalletTransactionService(Repository repository) {
        this.repository = repository;
    }

    /**
     * Enriches the given {@link WalletTransaction} and saves to repository.
     *
     * @param entity           The {@link WalletTransaction} to be enriched.
     * @param baseCurrencyRate The base currency rate used for calculation.
     */
    @Transactional
    public void enrichAndSave(WalletTransaction entity, Double baseCurrencyRate) {
        repository.save(entity.calculateBaseCurrencyAmount(baseCurrencyRate));
    }
}
