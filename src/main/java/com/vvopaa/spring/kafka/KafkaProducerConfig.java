package com.vvopaa.spring.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.vvopaa.spring.kafka.KafkaManager.EPAM_TOPIC;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic initializeEpamTopic() {
        return TopicBuilder.name(EPAM_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
