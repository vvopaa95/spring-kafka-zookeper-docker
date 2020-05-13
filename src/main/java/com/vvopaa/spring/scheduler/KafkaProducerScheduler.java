package com.vvopaa.spring.scheduler;

import com.vvopaa.spring.dto.EpamMessageDto;
import com.vvopaa.spring.kafka.KafkaManager;
import com.vvopaa.spring.service.EpamMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

import static com.vvopaa.spring.kafka.KafkaManager.EPAM_TOPIC;

@ConditionalOnProperty(value = "kafka.enabled", havingValue = "true")
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerScheduler {
    private final KafkaManager kafkaManager;
    private final KafkaTemplate<Long, EpamMessageDto> kafkaTemplate;
    private final EpamMessageService messageService;
    private final AtomicInteger partition = new AtomicInteger();
    private boolean isUp;

    @Scheduled(initialDelay = 5000, fixedDelay = 7000)
    public void produceMessage() {
        EpamMessageDto dto = createDto();
        int currentValue = partition.get();
        isUp = currentValue == 0 || (currentValue != 2 && isUp);
        int currentPartition = isUp ? partition.getAndIncrement() : partition.getAndDecrement();
        kafkaManager.sendTopicToKafka(kafkaTemplate, EPAM_TOPIC, 3L, dto, currentPartition);
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void getDbMessageState() {
        log.info("There are {} messages currently in the h2 database.", messageService.countMessages());
    }

    private EpamMessageDto createDto() {
        return new EpamMessageDto(1, "Message with time: " + (LocalTime.now().toNanoOfDay() / 1000000));
    }
}
