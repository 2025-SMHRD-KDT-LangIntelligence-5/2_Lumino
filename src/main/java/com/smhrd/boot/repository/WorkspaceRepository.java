package com.smhrd.boot.repository;

import com.smhrd.boot.entity.Mypage;
import com.smhrd.boot.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
}
