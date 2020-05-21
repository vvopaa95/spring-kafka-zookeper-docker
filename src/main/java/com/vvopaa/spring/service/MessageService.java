package com.vvopaa.spring.service;

import com.vvopaa.spring.dto.MessageDto;
import com.vvopaa.spring.entity.MessageEntity;
import com.vvopaa.spring.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageEntity save(MessageDto messageDto) {
        MessageEntity messageEntity = messageRepository.save(new MessageEntity(messageDto));
        log.info("Entity with id {} is created", messageEntity.getId());
        return messageEntity;
    }

    public long countMessages() {
        return messageRepository.count();
    }
}
