package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    // category_id로 도구 조회
    @Query("SELECT t FROM Tool t WHERE t.category_id = :categoryId")
    List<Tool> findByCategoryId(@Param("categoryId") Integer categoryId);
}
