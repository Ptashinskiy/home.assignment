package com.example.home.assignment.dao;

import com.example.home.assignment.domain.WalletTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Component that imitates storage repository behavior.
 * */
@Component
public class Repository {

    private final static Logger logger = LoggerFactory.getLogger(Repository.class);

    public void save(WalletTransaction entity) {
        logger.info("Entity saved to the storage.");
    }
}
