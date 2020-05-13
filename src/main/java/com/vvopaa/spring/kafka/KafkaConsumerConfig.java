package com.vvopaa.spring.kafka;

import com.vvopaa.spring.dto.EpamMessageDto;
import com.vvopaa.spring.service.EpamMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import static com.vvopaa.spring.kafka.KafkaManager.EPAM_TOPIC;

@Configuration
@EnableKafka
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerConfig {
    private static final String CONTAINER_FACTORY_METHOD = "batchFactory";
    private final ConsumerFactory<Long, EpamMessageDto> consumerFactory;
    private final EpamMessageService messageService;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, EpamMessageDto> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, EpamMessageDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setBatchListener(true);
        return factory;
    }

    @KafkaListener(topicPartitions = { @TopicPartition(topic = EPAM_TOPIC, partitions = {"0"}) },
            containerFactory = CONTAINER_FACTORY_METHOD)
    public void listen0(EpamMessageDto epamMessageDto, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) long key) {
        listenAndSaveMessage("listen0", epamMessageDto, key);
    }

    @KafkaListener(topicPartitions = { @TopicPartition(topic = EPAM_TOPIC, partitions = {"1"}) },
            containerFactory = CONTAINER_FACTORY_METHOD)
    public void listen1(EpamMessageDto epamMessageDto, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) long key) {
        listenAndSaveMessage("listen1", epamMessageDto, key);
    }

    @KafkaListener(topicPartitions = { @TopicPartition(topic = EPAM_TOPIC, partitions = {"2"}) },
            containerFactory = CONTAINER_FACTORY_METHOD)
    public void listen2(EpamMessageDto epamMessageDto, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) long key) {
        listenAndSaveMessage("listen2", epamMessageDto, key);
    }

    private void listenAndSaveMessage(String methodName, EpamMessageDto epamMessageDto, long kafkaKey) {
        log.info("{} - Received Message: {} with key: {}", methodName, epamMessageDto, kafkaKey);
        messageService.save(epamMessageDto);
    }
}
