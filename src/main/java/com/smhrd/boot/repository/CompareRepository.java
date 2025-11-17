package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Compare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompareRepository extends JpaRepository<Compare, Integer> {
    // tool_name으로 검색 (부분 일치)
    @Query("SELECT t FROM Compare t WHERE t.toolName LIKE CONCAT('%', :keyword, '%')")
    List<Compare> findByToolNameContaining(@Param("keyword") String keyword);
    
    // tool_name으로 정확히 일치하는 도구 조회 (대소문자 구분 없음)
    @Query("SELECT t FROM Compare t WHERE LOWER(TRIM(t.toolName)) = LOWER(TRIM(:toolName))")
    Compare findByToolName(@Param("toolName") String toolName);
}
