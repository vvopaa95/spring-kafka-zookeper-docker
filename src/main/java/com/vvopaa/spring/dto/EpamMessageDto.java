package com.vvopaa.spring.dto;

import com.vvopaa.spring.entity.EpamMessageEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpamMessageDto {
    private long messageId;
    private String message;

    public EpamMessageDto(EpamMessageEntity epamMessageEntity) {
        this.messageId = epamMessageEntity.getMessageId();
        this.message = epamMessageEntity.getMessage();
    }
}
