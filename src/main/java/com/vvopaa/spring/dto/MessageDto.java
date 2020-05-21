package com.vvopaa.spring.dto;

import com.vvopaa.spring.entity.MessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private long id;
    private long messageId;
    private String message;

    public MessageDto(MessageEntity messageEntity) {
        this.messageId = messageEntity.getMessageId();
        this.message = messageEntity.getMessage();
        this.id = messageEntity.getId();
    }

    public MessageDto(long messageId, String message) {
        this.messageId = messageId;
        this.message = message;
    }
}
