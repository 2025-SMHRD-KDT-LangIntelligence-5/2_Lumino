package com.smhrd.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FastAPIRequest {
    private List<ChatMessage> messages;  // 대화 히스토리 (시스템 프롬프트 포함)
}
