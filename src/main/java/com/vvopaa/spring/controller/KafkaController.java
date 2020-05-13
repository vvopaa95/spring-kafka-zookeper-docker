package com.vvopaa.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @GetMapping("/test-url/")
    public ResponseEntity<String> sendMessageToTopic() {
        return ResponseEntity.ok("ok");
    }

}
