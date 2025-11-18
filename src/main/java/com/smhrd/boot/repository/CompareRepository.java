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

    // tool_name으로 정확히 일치하는 도구 조회
    @Query("SELECT t FROM Compare t WHERE t.toolName = :toolName")
    Compare findByToolName(@Param("toolName") String toolName);

    // 랜덤으로 15개 도구 조회
    @Query(value = "SELECT * FROM tools ORDER BY RAND() LIMIT 15", nativeQuery = true)
    List<Compare> findRandom15Tools();
}
