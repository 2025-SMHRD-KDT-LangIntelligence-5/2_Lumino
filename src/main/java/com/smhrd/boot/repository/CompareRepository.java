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
    @Query("SELECT t FROM Compare t WHERE t.tool_name LIKE CONCAT('%', :keyword, '%')")
    List<Compare> findByToolNameContaining(@Param("keyword") String keyword);
}
