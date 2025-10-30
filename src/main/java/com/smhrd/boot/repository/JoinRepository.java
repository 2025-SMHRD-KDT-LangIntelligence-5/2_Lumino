package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<Join, Integer> {
}
