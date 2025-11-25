package com.smhrd.boot.repository;

import com.smhrd.boot.entity.ToolCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolCategoryRepository extends JpaRepository<ToolCategory, Long> {

    // 특정 카테고리에 속한 tool_id 목록 조회
    @Query(value = "SELECT DISTINCT tool_id FROM tool_categories WHERE category_id = :categoryId", nativeQuery = true)
    List<Integer> findToolIdsByCategoryId(@Param("categoryId") Integer categoryId);

    // 특정 도구의 모든 카테고리 조회
    @Query(value = "SELECT category_id FROM tool_categories WHERE tool_id = :toolId", nativeQuery = true)
    List<Integer> findCategoryIdsByToolId(@Param("toolId") Integer toolId);

    // 특정 도구의 기존 카테고리 삭제
    @Query(value = "DELETE FROM tool_categories WHERE tool_id = :toolId", nativeQuery = true)
    void deleteByToolId(@Param("toolId") Integer toolId);
}
