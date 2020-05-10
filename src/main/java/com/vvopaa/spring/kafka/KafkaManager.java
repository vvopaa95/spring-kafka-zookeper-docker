package com.vvopaa.spring.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaManager {
    public static final String EPAM_TOPIC = "EPAM_SECRET_MESSAGES_MINE1";

    public <K, V> void sendTopicToKafka(KafkaTemplate<K, V> kafkaTemplate, String topic, K key, V value, Integer partition) {
        kafkaTemplate.send(topic, partition, key, value).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<K, V> result) {
                log.info("Sent message=[{}] with key [{}] with offset=[{}] and partition: [{}]",
                        value, key, result.getRecordMetadata().offset(), partition);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[{}] with key [{}] and partition: [{}] due to: {}",
                        value, key, partition, ex.getMessage());
            }
        });
    }
}
