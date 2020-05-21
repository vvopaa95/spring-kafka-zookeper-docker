package com.vvopaa.spring.entity;

import com.vvopaa.spring.dto.MessageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class MessageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long messageId;
    private String message;

    public MessageEntity(MessageDto messageDto) {
        this.id = messageDto.getId();
        this.messageId = messageDto.getMessageId();
        this.message = messageDto.getMessage();
    }
}
