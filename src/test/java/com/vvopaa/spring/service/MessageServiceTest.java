package com.vvopaa.spring.service;

import com.vvopaa.spring.dto.MessageDto;
import com.vvopaa.spring.entity.MessageEntity;
import com.vvopaa.spring.repository.MessageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    @InjectMocks
    private MessageService messageService;
    @Mock
    private MessageRepository messageRepository;

    @Test
    void testSaveReturnNewMessage() {
        MessageDto messageDto = new MessageDto();
        MessageEntity messageEntity = new MessageEntity(messageDto);
        MessageEntity messageEntitySaved = new MessageEntity(messageDto);
        messageEntitySaved.setId(1);
        Mockito.when(messageRepository.save(messageEntity)).thenReturn(messageEntitySaved);
        Assertions.assertEquals(messageEntitySaved, messageService.save(messageDto));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 13, 17, 19})
    void testCountMessagesReturnMessageQuantity(long count) {
        Mockito.when(messageRepository.count()).thenReturn(count);
        Assertions.assertEquals(count, messageService.countMessages());
    }
}
