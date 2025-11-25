package com.smhrd.boot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="chat_contents")
public class Chatbot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    @Column
    private Long chat_session_id;  // 기존 세션 ID (NULL 허용)

    @Column
    private Long chatter;  // 채팅 참여자 ID (NULL 허용)

    @Column(nullable = false, length = 100)
    private String user_id;  // 사용자 ID

    @Column(nullable = false, length = 100)
    private String session_id;  // 대화 세션 ID

    @Column(nullable = false, length = 10)
    private String role;  // 'user' 또는 'assistant'

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;  // 메시지 내용

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;  // 생성 시간

    // 생성자 - 편의성을 위해 추가
    public Chatbot(String user_id, String session_id, String role, String content) {
        this.user_id = user_id;
        this.session_id = session_id;
        this.role = role;
        this.content = content;
    }
}
