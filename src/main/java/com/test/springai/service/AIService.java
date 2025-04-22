package com.test.springai.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

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

        ChatResponse response = chatClient.prompt()
                .user(u -> u.text(prompt).param("topic", topic))
                .call()
                .chatResponse();

        log.info("response: {}", response);

        assert response != null;
        return response.getResult().getOutput().getText();
    }
}
