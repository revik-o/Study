package io.hacken.task.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hacken.task.database.dao.transaction.AcceptedTransactionDao;
import io.hacken.task.database.model.AcceptedTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static io.hacken.task.configuration.DefaultValues.GENERIC_KAFKA_GROUP_ID;
import static io.hacken.task.configuration.DefaultValues.MB_TOPIC;

@Service
@RequiredArgsConstructor
public class ApacheKafkaTopicListener {

    private final AcceptedTransactionDao acceptedTransactionDao;
    private final ObjectMapper jacksonObjectMapper;

    @KafkaListener(topics = MB_TOPIC, groupId = GENERIC_KAFKA_GROUP_ID)
    void genericTopicListener(String serialisedArgs) throws JsonProcessingException {
        acceptedTransactionDao.save(this.jacksonObjectMapper.readValue(serialisedArgs, AcceptedTransaction.class));
    }
}
