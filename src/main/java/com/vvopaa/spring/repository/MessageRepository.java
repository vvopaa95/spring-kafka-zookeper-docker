package com.vvopaa.spring.repository;

import com.vvopaa.spring.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {

}
