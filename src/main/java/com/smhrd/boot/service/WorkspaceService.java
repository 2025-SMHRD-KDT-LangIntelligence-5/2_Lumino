package com.smhrd.boot.service;

import com.smhrd.boot.dto.ToolDTO;
import com.smhrd.boot.dto.WorkspaceWithToolsDTO;
import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.entity.Workspace;
import com.smhrd.boot.entity.WorkspaceItem;
import com.smhrd.boot.repository.CompareRepository;
import com.smhrd.boot.repository.WorkspaceItemRepository;
import com.smhrd.boot.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceItemRepository workspaceItemRepository;
    private final CompareRepository compareRepository;

    /**
     * 랜덤으로 5개의 tool_id를 선택해서 워크스페이스에 추가
     * @param workspaceId 워크스페이스 ID
     * @return 추가된 WorkspaceItem 리스트
     */
    @Transactional
    public List<WorkspaceItem> createWorkspaceWithRandomTools(Integer workspaceId) {
        // 1. 모든 tools 조회
        List<Compare> allTools = compareRepository.findAll();

        // 2. tool_id 리스트로 변환
        List<Integer> toolIds = new ArrayList<>();
        for (Compare tool : allTools) {
            toolIds.add(tool.getTool_id());
        }

        // 3. 랜덤으로 섞기
        Collections.shuffle(toolIds);

        // 4. 최대 5개 선택 (tools가 5개보다 적으면 그만큼만)
        int count = Math.min(5, toolIds.size());
        List<WorkspaceItem> workspaceItems = new ArrayList<>();

        // 5. workspace_items에 삽입
        for (int i = 0; i < count; i++) {
            WorkspaceItem item = new WorkspaceItem();
            item.setWorkspaceId(workspaceId);
            item.setToolId(toolIds.get(i));
            WorkspaceItem savedItem = workspaceItemRepository.save(item);
            workspaceItems.add(savedItem);
        }

        return workspaceItems;
    }

    /**
     * 워크스페이스의 모든 아이템 조회
     * @param workspaceId 워크스페이스 ID
     * @return WorkspaceItem 리스트
     */
    public List<WorkspaceItem> getWorkspaceItems(Integer workspaceId) {
        return workspaceItemRepository.findByWorkspaceId(workspaceId);
    }

    /**
     * 모든 워크스페이스와 각 워크스페이스의 tool 정보를 함께 조회
     * @return WorkspaceWithToolsDTO 리스트
     */
    public List<WorkspaceWithToolsDTO> getAllWorkspacesWithTools() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        List<WorkspaceWithToolsDTO> result = new ArrayList<>();

        for (Workspace workspace : workspaces) {
            // 워크스페이스의 아이템들 조회
            List<WorkspaceItem> items = workspaceItemRepository.findByWorkspaceId(workspace.getWorkspace_id());

            // 각 아이템의 tool 정보 조회
            List<ToolDTO> tools = new ArrayList<>();
            for (WorkspaceItem item : items) {
                Optional<Compare> toolOpt = compareRepository.findById(item.getToolId());
                if (toolOpt.isPresent()) {
                    Compare tool = toolOpt.get();
                    tools.add(new ToolDTO(tool.getTool_id(), tool.getTool_name()));
                }
            }

            // DTO 생성
            WorkspaceWithToolsDTO dto = new WorkspaceWithToolsDTO(
                workspace.getWorkspace_id(),
                workspace.getWorkspace_name(), // 데이터베이스의 workspace_name 사용
                tools
            );
            result.add(dto);
        }

        return result;
    }
}
