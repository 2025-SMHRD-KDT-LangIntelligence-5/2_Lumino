package com.smhrd.boot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatGPTService implements ChatGPTS {

    // Spring AI가 자동 등록한 ChatClient 빈을 주입 받습니다.
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    private final AiChatHistoryService aiChatHistoryService;

    @Override
    public AiChatResponseDto getAiRecommendation(Long memberId, String sessionId, String prompt) {
        // ChatClient를 사용해 AI에게 요청
        String response = chatClient.prompt().user(prompt).call().content();

        // JSON 응답 파싱
        String recommendation;
        try {
            Map<String, String> responseMap = objectMapper.readValue(response, Map.class);
            recommendation = Optional.ofNullable(responseMap.get("recommendation"))
                    .orElse("추천할 메뉴가 없습니다.").trim();
        } catch (Exception exception) {
            recommendation = "추천할 메뉴를 파싱하는 데 실패했습니다.";
        }

        // AI 추천 히스토리 MySQL + Redis에 저장
        aiChatHistoryService.saveChatHistory(memberId, sessionId, recommendation);

        return new AiChatResponseDto(recommendation);
    }
}