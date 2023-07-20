package com.example.home.assignment.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Component that imitates storage repository behavior.
 * */
@Component
public class WalletTransactionRepository {

    private final static Logger logger = LoggerFactory.getLogger(WalletTransactionRepository.class);

    public void save(WalletTransaction entity) {
        logger.info("Entity saved to the storage.");
    }
}
