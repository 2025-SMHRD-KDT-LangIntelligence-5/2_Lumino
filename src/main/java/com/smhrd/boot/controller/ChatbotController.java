package com.smhrd.boot.controller;

import com.smhrd.boot.dto.ChatMessage;
import com.smhrd.boot.dto.ChatRequest;
import com.smhrd.boot.service.ChatbotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ChatbotController {

    private final ChatbotService chatbotService;

    /**
     * 챗봇 메시지 처리 (대화 히스토리 포함)
     */
    @PostMapping("/message")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody ChatRequest request) {
        try {
            String message = request.getMessage();
            String sessionId = request.getSessionId();
            String userId = request.getUserId() != null ? request.getUserId() : "anonymous";

            if (message == null || message.trim().isEmpty()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("response", "메시지를 입력해주세요.");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            // Service에서 히스토리 관리 + FastAPI 호출
            Map<String, String> response = chatbotService.processChat(message, sessionId, userId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.err.println("챗봇 처리 오류: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("response", "죄송합니다. 챗봇 서버에 문제가 발생했습니다. 잠시 후 다시 시도해주세요.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * 특정 세션의 대화 히스토리 조회
     */
    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<List<ChatMessage>> getSessionHistory(@PathVariable String sessionId) {
        try {
            List<ChatMessage> history = chatbotService.getSessionHistory(sessionId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            System.err.println("히스토리 조회 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 사용자의 모든 세션 목록 조회
     */
    @GetMapping("/sessions")
    public ResponseEntity<List<Map<String, Object>>> getUserSessions(
            @RequestParam(defaultValue = "anonymous") String userId) {
        try {
            List<Map<String, Object>> sessions = chatbotService.getUserSessions(userId);
            return ResponseEntity.ok(sessions);
        } catch (Exception e) {
            System.err.println("세션 목록 조회 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
