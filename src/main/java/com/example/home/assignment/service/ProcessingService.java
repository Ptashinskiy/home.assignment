package com.example.home.assignment.service;

import com.example.home.assignment.dto.WalletTransactionMessage;
import com.example.home.assignment.exception.CurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that processes wallet transaction messages.
 */
@Service
public class ProcessingService {

    private final static Logger logger = LoggerFactory.getLogger(ProcessingService.class);

    private final WalletTransactionService walletTransactionService;
    private final CurrencyService currencyService;

    @Autowired
    public ProcessingService(WalletTransactionService walletTransactionService, CurrencyService currencyService) {
        this.walletTransactionService = walletTransactionService;
        this.currencyService = currencyService;
    }

    /**
     * Processes the given {@link WalletTransactionMessage} by retrieving the currency rate and enriching the entity.
     *
     * @param message The {@link WalletTransactionMessage} to be processed.
     */
    public void process(WalletTransactionMessage message) {
        try {
            var currencyRate = currencyService.getCurrencyRate(
                    message.currency(),
                    message.baseCurrency(),
                    message.timestamp()
            );
            walletTransactionService.enrichAndSave(message.toEntity(), currencyRate.value());
        } catch (CurrencyException exception) {
            logger.error("Failed to process the message. Unable to receive currency rate. " +
                         "Player id: {}, Game id: {}, Transaction id: {}",
                         message.playerId(),
                         message.gameId(),
                         message.transactionId()
            );
        }
    }
}
