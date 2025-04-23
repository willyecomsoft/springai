package com.test.springai.service;

import com.test.springai.dto.BookDetail;
import com.test.springai.prompt.BookPrompt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIService {

    private final ChatClient chatClient;

    public String getJoke(String topic) {
        String prompt = """
                你將扮演兩位相聲演員，主要語言為台灣繁體中文，
                其中一位嚴肅，其中一位講話瘋癲，
                請兩位給我講一個關於{topic}的段子
                """;

//        ChatResponse response = chatClient.prompt()
//                .user(u -> u.text(prompt).param("topic", topic))
//                .call()
//                .chatResponse();
//
//        log.info("response: {}", response);
//
//        assert response != null;
//        return response.getResult().getOutput().getText();

        return chatClient.prompt()
                .user(u -> u.text(prompt).param("topic", topic))
                .call()
                .content();
    }

    public Flux<String> getJokeFlux(String topic) {
        String prompt = """
                你將扮演兩位相聲演員，主要語言為台灣繁體中文，
                其中一位嚴肅，其中一位講話瘋癲，
                請兩位給我講一個關於{topic}的段子
                """;

        return chatClient.prompt()
                .user(u -> u.text(prompt).param("topic", topic))
                .stream()
                .content();
    }


    public String getBooks(String category, String year) {
        return chatClient.prompt()
                .user(u -> u.text(BookPrompt.DEFAULT)
                        .param("category", category)
                        .param("year", year)
                )
                .call()
                .content();
    }

    public List<BookDetail> getBooksDto(String category, String year) {
        return chatClient.prompt()
                .user(u -> u.text("請建議我幾本 {year} 年時 {category} 類型的暢銷書。")
                        .param("category", category)
                        .param("year", year)
                )
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

}
