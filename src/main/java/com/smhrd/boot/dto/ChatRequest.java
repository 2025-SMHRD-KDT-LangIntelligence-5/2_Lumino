package com.smhrd.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String message;        // 사용자 메시지
    private String sessionId;      // 세션 ID (선택사항)
    private String userId;         // 사용자 ID (기본: "anonymous")
}
