package com.example.home.assignment.messaging;

import com.example.home.assignment.service.ProcessingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListenerTest {

    @InjectMocks
    private Listener listener;

    @Mock
    public ProcessingService processingService;

    @Test
    void delegatesToProcessingService() {
        var message = new WalletTransactionMessage(
                "BET",
                "123424120",
                "european_roulette",
                "7d7a0b82-0c47-11ee-be56-0242ac120002",
                "2023-06-16T06:00:00.000Z",
                10.0,
                "USD",
                "EUR"
        );

        listener.listen(message);

        verify(processingService).process(message);
    }

}
