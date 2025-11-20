package com.smhrd.boot.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.model}")
    private String model;

    private final Gson gson = new Gson();

    // 대화 히스토리를 저장 (실제로는 세션이나 DB에 저장해야 함)
    private final Map<String, List<Map<String, String>>> conversationHistory = new HashMap<>();

    /**
     * ChatGPT API를 호출하여 응답을 받습니다
     * @param userMessage 사용자 메시지
     * @param sessionId 세션 ID (대화 히스토리 관리용)
     * @return ChatGPT 응답
     */
    public String getChatGPTResponse(String userMessage, String sessionId) {
        // API 키가 설정되지 않은 경우 기본 응답 반환
        if (apiKey == null || apiKey.equals("YOUR_OPENAI_API_KEY_HERE") || apiKey.isEmpty()) {
            log.warn("OpenAI API key is not configured. Using fallback response.");
            return getFallbackResponse(userMessage);
        }

        try {
            // 대화 히스토리 가져오기 또는 생성
            List<Map<String, String>> messages = conversationHistory.computeIfAbsent(sessionId, k -> {
                List<Map<String, String>> newHistory = new ArrayList<>();
                // 시스템 메시지 추가
                Map<String, String> systemMessage = new HashMap<>();
                systemMessage.put("role", "system");
                systemMessage.put("content", "당신은 A-VERSE AI 어시스턴트입니다. A-VERSE는 다양한 AI 도구들을 탐색하고 비교할 수 있는 플랫폼입니다. 사용자의 질문에 친절하고 정확하게 답변해주세요.");
                newHistory.add(systemMessage);
                return newHistory;
            });

            // 사용자 메시지 추가
            Map<String, String> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.add(userMsg);

            // WebClient 생성
            WebClient webClient = WebClient.builder()
                    .baseUrl(apiUrl)
                    .defaultHeader("Content-Type", "application/json")
                    .defaultHeader("Authorization", "Bearer " + apiKey)
                    .build();

            // 요청 바디 생성
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("model", model);
            requestBody.addProperty("max_tokens", 500);
            requestBody.addProperty("temperature", 0.7);

            JsonArray messagesArray = new JsonArray();
            for (Map<String, String> msg : messages) {
                JsonObject msgObj = new JsonObject();
                msgObj.addProperty("role", msg.get("role"));
                msgObj.addProperty("content", msg.get("content"));
                messagesArray.add(msgObj);
            }
            requestBody.add("messages", messagesArray);

            // API 호출
            String response = webClient.post()
                    .bodyValue(requestBody.toString())
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();

            // 응답 파싱
            JsonObject responseJson = gson.fromJson(response, JsonObject.class);
            String assistantMessage = responseJson
                    .getAsJsonArray("choices")
                    .get(0)
                    .getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content")
                    .getAsString();

            // 어시스턴트 응답을 히스토리에 추가
            Map<String, String> assistantMsg = new HashMap<>();
            assistantMsg.put("role", "assistant");
            assistantMsg.put("content", assistantMessage);
            messages.add(assistantMsg);

            // 히스토리가 너무 길어지면 오래된 메시지 삭제 (시스템 메시지는 유지)
            if (messages.size() > 21) { // 시스템 메시지 1개 + 대화 10쌍
                messages.subList(1, messages.size() - 20).clear();
            }

            return assistantMessage;

        } catch (Exception e) {
            log.error("Error calling ChatGPT API: ", e);
            return "죄송합니다. 일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.";
        }
    }

    /**
     * API 키가 없을 때 사용하는 기본 응답
     */
    private String getFallbackResponse(String message) {
        if (message == null || message.trim().isEmpty()) {
            return "메시지를 입력해주세요.";
        }

        String lowerMessage = message.toLowerCase();

        if (lowerMessage.contains("안녕") || lowerMessage.contains("hello") || lowerMessage.contains("hi")) {
            return "안녕하세요! A-VERSE 챗봇입니다. 무엇을 도와드릴까요?";
        }

        if (lowerMessage.contains("a-verse") || lowerMessage.contains("에이버스") || lowerMessage.contains("소개")) {
            return "A-VERSE는 다양한 AI 도구들을 한곳에서 비교하고 관리할 수 있는 플랫폼입니다. AI 도구 카테고리별 검색, 비교, 워크스페이스 관리 기능을 제공합니다.";
        }

        if (lowerMessage.contains("기능") || lowerMessage.contains("할 수 있") || lowerMessage.contains("뭐")) {
            return "다음과 같은 기능을 제공합니다:\n" +
                   "1. AI 도구 카테고리별 탐색 (코딩, 이미지, 비디오, 텍스트 등)\n" +
                   "2. AI 도구 비교 기능\n" +
                   "3. 워크스페이스 관리\n" +
                   "4. 커뮤니티 공유 기능";
        }

        if (lowerMessage.contains("도움") || lowerMessage.contains("help")) {
            return "무엇이 궁금하신가요? 다음 주제에 대해 물어보실 수 있습니다:\n" +
                   "- A-VERSE 소개\n" +
                   "- 제공 기능\n" +
                   "- AI 도구 카테고리\n" +
                   "- 사용 방법";
        }

        if (lowerMessage.contains("카테고리") || lowerMessage.contains("분류")) {
            return "다음 카테고리의 AI 도구들을 제공합니다:\n" +
                   "- 코딩 (Coding)\n" +
                   "- 이미지 (Image)\n" +
                   "- 비디오 (Video)\n" +
                   "- 오디오 (Audio)\n" +
                   "- 텍스트 (Text)\n" +
                   "- 검색 (Search)\n" +
                   "- 교육 (Education)";
        }

        return "죄송합니다. 잘 이해하지 못했습니다. 'A-VERSE 소개', '기능', '카테고리', '도움말' 등에 대해 물어보실 수 있습니다.\n\n" +
               "※ ChatGPT API 키가 설정되지 않아 제한된 응답만 제공됩니다. application.properties에 API 키를 설정해주세요.";
    }

    /**
     * 특정 세션의 대화 히스토리 초기화
     */
    public void clearHistory(String sessionId) {
        conversationHistory.remove(sessionId);
    }
}
