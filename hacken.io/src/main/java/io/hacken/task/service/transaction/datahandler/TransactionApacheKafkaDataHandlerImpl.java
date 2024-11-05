package io.hacken.task.service.transaction.datahandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hacken.task.database.model.AcceptedTransaction;
import io.hacken.task.service.transaction.trotting.TransactionTrottingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import static io.hacken.task.configuration.DefaultValues.MB_TOPIC;
import static java.util.concurrent.CompletableFuture.runAsync;

@Slf4j
@RequiredArgsConstructor
public class TransactionApacheKafkaDataHandlerImpl implements TransactionDataHandlerI {

    private final ObjectMapper jacksonObjectMapper;
    private final CommonTransactionBuffer transactionBuffer;
    private final TransactionTrottingHandler trottingHandler;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    private byte[] convertToBytes(Object object) {
        try {
            return this.jacksonObjectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException ignore) {
            throw new IllegalArgumentException("Smth went wrong");
        }
    }

    @Override
    public void handleAcceptedTransaction(AcceptedTransaction transaction) {
        if (this.trottingHandler.isNotTimeForTrotting()) {
            this.kafkaTemplate.send(MB_TOPIC, this.convertToBytes(transaction));

            if (this.transactionBuffer.isNotEmpty()) {
                runAsync(() -> {
                    this.transactionBuffer.free().forEach(this::handleAcceptedTransaction);
                    log.info("All data received!!!");
                });
            }
        } else {
            this.transactionBuffer.collect(transaction);
        }
    }
}
