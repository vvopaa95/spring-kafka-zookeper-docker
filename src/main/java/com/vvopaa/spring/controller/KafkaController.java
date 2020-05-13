package com.vvopaa.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
    /*@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;*/



    @GetMapping("/kafka/{message}")
    public ResponseEntity<String> sendMessageToTopic(@PathVariable String message) {
        return ResponseEntity.ok("ok");
    }

}
