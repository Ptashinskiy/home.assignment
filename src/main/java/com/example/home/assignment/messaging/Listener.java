package com.example.home.assignment.messaging;

import com.example.home.assignment.dto.WalletTransactionMessage;
import com.example.home.assignment.service.ProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Component class that listens to Kafka messages and processes them.
 */
@Component
public class Listener {

    private final static Logger logger = LoggerFactory.getLogger(Listener.class);

    private final ProcessingService processingService;

    @Autowired
    public Listener(ProcessingService processingService) {
        this.processingService = processingService;
    }

    /**
     * Kafka listener method that processes incoming messages.
     *
     * @param message The {@link WalletTransactionMessage} received from Kafka.
     */
    @KafkaListener(topics = "wallet-transactions")
    public void listen(WalletTransactionMessage message) {
        logger.info(
                "Received new message. Player id: {}, Game id: {}, Transaction id: {}",
                message.playerId(),
                message.gameId(),
                message.transactionId()
        );
        processingService.process(message);
    }
}
