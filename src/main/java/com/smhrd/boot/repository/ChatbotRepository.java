package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Chatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotRepository extends JpaRepository<Chatbot, Integer> {

}
