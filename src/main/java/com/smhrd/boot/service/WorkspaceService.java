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
            toolIds.add(tool.getToolId());
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
                    tools.add(new ToolDTO(tool.getToolId(), tool.getToolName()));
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

    /**
     * 모든 워크스페이스 조회
     * @return Workspace 리스트
     */
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    /**
     * 특정 워크스페이스와 도구 정보 조회
     * @param workspaceId 워크스페이스 ID
     * @return WorkspaceWithToolsDTO
     */
    public WorkspaceWithToolsDTO getWorkspaceWithTools(Integer workspaceId) {
        Optional<Workspace> workspaceOpt = workspaceRepository.findById(workspaceId);
        if (!workspaceOpt.isPresent()) {
            return null;
        }

        Workspace workspace = workspaceOpt.get();
        List<WorkspaceItem> items = workspaceItemRepository.findByWorkspaceId(workspaceId);

        // 각 아이템의 tool 정보 조회
        List<ToolDTO> tools = new ArrayList<>();
        for (WorkspaceItem item : items) {
            Optional<Compare> toolOpt = compareRepository.findById(item.getToolId());
            if (toolOpt.isPresent()) {
                Compare tool = toolOpt.get();
                tools.add(new ToolDTO(tool.getToolId(), tool.getToolName()));
            }
        }

        return new WorkspaceWithToolsDTO(
            workspace.getWorkspace_id(),
            workspace.getWorkspace_name(),
            tools
        );
    }

    /**
     * 워크스페이스에 특정 도구 추가
     * @param workspaceId 워크스페이스 ID
     * @param toolId 추가할 도구 ID
     * @return 추가된 WorkspaceItem
     */
    @Transactional
    public WorkspaceItem addToolToWorkspace(Integer workspaceId, Integer toolId) {
        // 이미 존재하는지 확인
        List<WorkspaceItem> existingItems = workspaceItemRepository.findByWorkspaceId(workspaceId);
        for (WorkspaceItem item : existingItems) {
            if (item.getToolId().equals(toolId)) {
                // 이미 존재하면 null 반환
                return null;
            }
        }

        // 새로운 WorkspaceItem 생성 및 저장
        WorkspaceItem newItem = new WorkspaceItem();
        newItem.setWorkspaceId(workspaceId);
        newItem.setToolId(toolId);
        return workspaceItemRepository.save(newItem);
    }

    /**
     * 워크스페이스 전체 저장 (workspace_name과 tool_name 배열을 받아서 저장)
     * @param workspaceName 워크스페이스 이름
     * @param toolNames 도구 이름 배열
     * @return 생성된 Workspace
     */
    @Transactional
    public Workspace saveWorkspaceWithTools(String workspaceName, List<String> toolNames) {
        // 1. 새로운 Workspace 생성
        Workspace workspace = new Workspace();
        workspace.setWorkspace_name(workspaceName);
        Workspace savedWorkspace = workspaceRepository.save(workspace);

        // 2. tool_name으로 tool_id를 찾아서 workspace_items에 저장
        for (String toolName : toolNames) {
            Compare tool = compareRepository.findByToolName(toolName);
            if (tool != null) {
                WorkspaceItem item = new WorkspaceItem();
                item.setWorkspaceId(savedWorkspace.getWorkspace_id());
                item.setToolId(tool.getToolId());
                workspaceItemRepository.save(item);
            }
        }

        return savedWorkspace;
    }
}
