package com.vvopaa.spring.service;

import com.vvopaa.spring.dto.EpamMessageDto;
import com.vvopaa.spring.entity.EpamMessageEntity;
import com.vvopaa.spring.repository.EpamMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EpamMessageService {
    private final EpamMessageRepository epamMessageRepository;

    public void save(EpamMessageDto epamMessageDto) {
        EpamMessageEntity messageEntity = epamMessageRepository.save(new EpamMessageEntity(epamMessageDto));
        log.info("Entity with id {} is created", messageEntity.getId());
    }

    public long countMessages() {
        return epamMessageRepository.count();
    }
}
