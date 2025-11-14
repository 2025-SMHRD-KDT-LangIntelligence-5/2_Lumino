package com.smhrd.boot.repository;

import com.smhrd.boot.entity.WorkspaceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceItemRepository extends JpaRepository<WorkspaceItem, Integer> {
    // workspace_id로 workspace_items 조회
    List<WorkspaceItem> findByWorkspaceId(Integer workspaceId);
}
