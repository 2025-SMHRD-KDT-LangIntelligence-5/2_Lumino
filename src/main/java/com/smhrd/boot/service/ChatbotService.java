package com.smhrd.boot.service;

import com.smhrd.boot.dto.ChatMessage;
import com.smhrd.boot.dto.FastAPIRequest;
import com.smhrd.boot.entity.Chatbot;
import com.smhrd.boot.repository.ChatbotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatbotService {

    private final ChatbotRepository chatbotRepository;
    private static final String FASTAPI_URL = "http://localhost:5000/chat/simple";
    private static final int MAX_HISTORY_SIZE = 10;  // 최대 히스토리 개수

    /**
     * 대화 처리 메인 로직
     * 1. 히스토리 로드
     * 2. FastAPI 호출
     * 3. 히스토리 저장
     */
    @Transactional
    public Map<String, String> processChat(String message, String sessionId, String userId) {
        // 1. 세션 ID가 없으면 생성
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = UUID.randomUUID().toString();
        }

        // 2. 대화 히스토리 로드 (최근 10개)
        List<ChatMessage> history = loadChatHistory(sessionId);

        // 3. 현재 사용자 메시지 추가
        ChatMessage userMessage = new ChatMessage("user", message);
        history.add(userMessage);

        // 4. FastAPI에 전달 (히스토리 포함)
        String aiResponse = callFastAPI(history);

        // 5. DB에 저장 (사용자 메시지 + AI 응답)
        saveMessage(userId, sessionId, "user", message);
        saveMessage(userId, sessionId, "assistant", aiResponse);

        // 6. 응답 반환
        Map<String, String> response = new HashMap<>();
        response.put("response", aiResponse);
        response.put("sessionId", sessionId);

        return response;
    }

    /**
     * 대화 히스토리 로드 (최근 N개)
     */
    private List<ChatMessage> loadChatHistory(String sessionId) {
        List<Chatbot> chatHistory = chatbotRepository.findRecentMessagesDesc(sessionId, MAX_HISTORY_SIZE);

        // 역순 정렬 (시간순으로 되돌림)
        Collections.reverse(chatHistory);

        return chatHistory.stream()
                .map(chat -> new ChatMessage(chat.getRole(), chat.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * 메시지 저장
     */
    private void saveMessage(String userId, String sessionId, String role, String content) {
        Chatbot chatbot = new Chatbot(userId, sessionId, role, content);
        chatbotRepository.save(chatbot);
    }

    /**
     * FastAPI 호출 (단순 AI 엔진)
     */
    private String callFastAPI(List<ChatMessage> messages) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            FastAPIRequest request = new FastAPIRequest(messages);
            HttpEntity<FastAPIRequest> entity = new HttpEntity<>(request, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    FASTAPI_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            return (String) response.getBody().get("response");

        } catch (Exception e) {
            System.err.println("FastAPI 호출 오류: " + e.getMessage());
            e.printStackTrace();
            return "죄송합니다. AI 서버에 문제가 발생했습니다.";
        }
    }

    /**
     * 특정 세션의 전체 히스토리 조회
     */
    public List<ChatMessage> getSessionHistory(String sessionId) {
        return chatbotRepository.findBySessionIdOrderByCreatedAtAsc(sessionId).stream()
                .map(chat -> new ChatMessage(chat.getRole(), chat.getContent()))
                .collect(Collectors.toList());
    }

    /**
     * 사용자의 모든 세션 조회
     */
    public List<Map<String, Object>> getUserSessions(String userId) {
        List<Object[]> results = chatbotRepository.findSessionsByUserId(userId);

        return results.stream()
                .map(row -> {
                    Map<String, Object> session = new HashMap<>();
                    session.put("sessionId", row[0]);
                    session.put("userId", row[1]);
                    session.put("lastMessageAt", row[2]);
                    session.put("messageCount", row[3]);
                    return session;
                })
                .collect(Collectors.toList());
    }
}
