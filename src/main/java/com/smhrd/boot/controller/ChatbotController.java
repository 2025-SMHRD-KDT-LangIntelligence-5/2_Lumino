package com.smhrd.boot.controller;

import com.google.gson.Gson;
import com.smhrd.boot.service.ChatGPTService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ChatbotController {

    private final Gson gson = new Gson();
    private final ChatGPTService chatGPTService;

    // 챗봇 페이지 매핑
    @GetMapping("/chatbot_page")
    public String chatbotPage() {
        return "chatbot_page";
    }

    // 챗봇 메시지 처리 REST API
    @PostMapping("/api/chatbot/message")
    @ResponseBody
    public String handleMessage(@RequestBody Map<String, String> request, HttpSession session) {
        String userMessage = request.get("message");

        // 세션 ID 가져오기 또는 생성
        String sessionId = (String) session.getAttribute("chatSessionId");
        if (sessionId == null) {
            sessionId = UUID.randomUUID().toString();
            session.setAttribute("chatSessionId", sessionId);
        }

        // ChatGPT API를 통해 응답 생성
        String botResponse = chatGPTService.getChatGPTResponse(userMessage, sessionId);

        Map<String, String> response = new HashMap<>();
        response.put("response", botResponse);

        return gson.toJson(response);
    }

    // 대화 히스토리 초기화 API (선택사항)
    @PostMapping("/api/chatbot/clear")
    @ResponseBody
    public String clearHistory(HttpSession session) {
        String sessionId = (String) session.getAttribute("chatSessionId");
        if (sessionId != null) {
            chatGPTService.clearHistory(sessionId);
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "대화 히스토리가 초기화되었습니다.");

        return gson.toJson(response);
    }
}
