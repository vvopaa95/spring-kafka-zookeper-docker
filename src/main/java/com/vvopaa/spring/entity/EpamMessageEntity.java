package com.vvopaa.spring.entity;

import com.vvopaa.spring.dto.EpamMessageDto;
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
public class EpamMessageEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long messageId;
    private String message;

    public EpamMessageEntity(EpamMessageDto epamMessageDto) {
        this.messageId = epamMessageDto.getMessageId();
        this.message = epamMessageDto.getMessage();
    }
}
