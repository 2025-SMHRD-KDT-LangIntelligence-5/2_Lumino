package com.smhrd.boot.service;

import org.springframework.stereotype.Service;

// 이 어노테이션을 추가해야 Spring이 이 클래스를 빈(Bean)으로 등록하고,
// ChatbotController에서 주입(DI) 받아 사용할 수 있습니다.
@Service
public class ChatGPTService {

    // TODO: 여기에 실제 ChatGPT API 연동 및 비즈니스 로직을 구현해야 합니다.

    // ChatbotController에서 호출하는 메서드 스켈레톤
    public String getChatGPTResponse(String userMessage, String sessionId) {
        // 임시 응답 (구현 전까지)
        return "죄송합니다, 잠시 후 다시 시도해주세요.";
    }

    // 대화 히스토리 초기화 메서드 스켈레톤 (옵션)
    public void clearHistory(String sessionId) {
        // 히스토리 초기화 로직
    }
}