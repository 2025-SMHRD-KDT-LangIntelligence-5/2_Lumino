package com.smhrd.boot.repository;

import com.smhrd.boot.entity.FixedTools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedToolsRepository extends JpaRepository<FixedTools, Integer> {
    // display_order로 정렬해서 모든 고정된 도구 조회
    @Query("SELECT f FROM FixedTools f ORDER BY f.displayOrder ASC")
    List<FixedTools> findAllOrderedByDisplayOrder();

    // 고정된 도구가 있는지 확인
    long count();
}

