package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Long> {

    // 특정 세션의 대화 히스토리 조회 (시간순 정렬) - Native Query로 변경
    @Query(value = "SELECT * FROM chat_contents WHERE session_id = :sessionId ORDER BY created_at ASC", nativeQuery = true)
    List<Chatbot> findBySessionIdOrderByCreatedAtAsc(@Param("sessionId") String sessionId);

    // 특정 세션의 최근 N개 메시지 조회 (역순으로 조회 후 뒤집어야 함)
    @Query(value = "SELECT * FROM chat_contents WHERE session_id = :sessionId ORDER BY created_at DESC LIMIT :limit", nativeQuery = true)
    List<Chatbot> findRecentMessagesDesc(@Param("sessionId") String sessionId, @Param("limit") int limit);

    // 사용자의 모든 세션 조회 (최근 대화순)
    @Query(value = "SELECT session_id, user_id, MAX(created_at) as last_message_at, COUNT(*) as message_count " +
                   "FROM chat_contents WHERE user_id = :userId " +
                   "GROUP BY session_id, user_id ORDER BY last_message_at DESC", nativeQuery = true)
    List<Object[]> findSessionsByUserId(@Param("userId") String userId);

    // 특정 세션 존재 여부 확인
    @Query(value = "SELECT COUNT(*) > 0 FROM chat_contents WHERE session_id = :sessionId", nativeQuery = true)
    boolean existsBySessionId(@Param("sessionId") String sessionId);

    // 특정 세션의 메시지 개수
    @Query(value = "SELECT COUNT(*) FROM chat_contents WHERE session_id = :sessionId", nativeQuery = true)
    long countBySessionId(@Param("sessionId") String sessionId);
}
