package com.test.springai.controller;

import com.test.springai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AIController {

    private final AIService aiService;

    @GetMapping
    public String test(@RequestParam String topic) {
        return aiService.getJoke(topic);
    }
}
