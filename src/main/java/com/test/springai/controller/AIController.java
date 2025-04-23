package com.test.springai.controller;

import com.test.springai.dto.BookDetail;
import com.test.springai.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AIController {

    private final AIService aiService;

    @GetMapping("/joke")
    public String getJoke(@RequestParam String topic) {
        return aiService.getJoke(topic);
    }

    @GetMapping(value = "/stream/joke", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getJokeFlux(@RequestParam String topic) {
        return aiService.getJokeFlux(topic);
    }

    @GetMapping("/books")
    public String getBooks(@RequestParam String category, @RequestParam String year) {
        return aiService.getBooks(category, year);
    }

    @GetMapping("/books-dto")
    public List<BookDetail> getBooksDtp(@RequestParam String category, @RequestParam String year) {
        return aiService.getBooksDto(category, year);
    }
}
