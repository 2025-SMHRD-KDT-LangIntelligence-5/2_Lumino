package com.smhrd.boot.controller;

import com.smhrd.boot.dto.SaveWorkspaceRequest;
import com.smhrd.boot.entity.Workspace;
import com.smhrd.boot.entity.WorkspaceItem;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    // 워크스페이스 관리 페이지 매핑
    @GetMapping("/workspace_page")
    public String workspace(Workspace workspace) {
        return "workspace_page"; // templates 폴더의 account-notifications.html을 찾음
    }

    /**
     * 워크스페이스에 랜덤으로 5개의 tool 추가
     * @param workspaceId 워크스페이스 ID
     * @return 추가된 WorkspaceItem 리스트
     */
    @PostMapping("/api/workspace/{workspaceId}/random-tools")
    @ResponseBody
    public ResponseEntity<List<WorkspaceItem>> createRandomTools(@PathVariable Integer workspaceId) {
        List<WorkspaceItem> items = workspaceService.createWorkspaceWithRandomTools(workspaceId);
        return ResponseEntity.ok(items);
    }

    /**
     * 워크스페이스의 모든 아이템 조회
     * @param workspaceId 워크스페이스 ID
     * @return WorkspaceItem 리스트
     */
    @GetMapping("/api/workspace/{workspaceId}/items")
    @ResponseBody
    public ResponseEntity<List<WorkspaceItem>> getWorkspaceItems(@PathVariable Integer workspaceId) {
        List<WorkspaceItem> items = workspaceService.getWorkspaceItems(workspaceId);
        return ResponseEntity.ok(items);
    }

    /**
     * 워크스페이스에 특정 도구 추가
     * @param workspaceId 워크스페이스 ID
     * @param toolId 추가할 도구 ID
     * @return 추가된 WorkspaceItem 또는 에러 메시지
     */
    @PostMapping("/api/workspace/{workspaceId}/add-tool/{toolId}")
    @ResponseBody
    public ResponseEntity<?> addToolToWorkspace(@PathVariable Integer workspaceId, @PathVariable Integer toolId) {
        WorkspaceItem item = workspaceService.addToolToWorkspace(workspaceId, toolId);
        if (item == null) {
            return ResponseEntity.badRequest().body("Tool already exists in workspace");
        }
        return ResponseEntity.ok(item);
    }

    /**
     * 워크스페이스 전체 저장 (workspace_name과 tool_name 배열을 받아서 저장)
     * @param request SaveWorkspaceRequest (workspaceName, toolNames)
     * @return 생성된 Workspace
     */
    @PostMapping("/api/workspace/save")
    @ResponseBody
    public ResponseEntity<?> saveWorkspace(@RequestBody SaveWorkspaceRequest request) {
        try {
            Workspace workspace = workspaceService.saveWorkspaceWithTools(
                request.getWorkspaceName(),
                request.getToolNames()
            );
            return ResponseEntity.ok(workspace);
        } catch (Exception e) {
            log.error("워크스페이스 저장 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("워크스페이스 저장에 실패했습니다: " + e.getMessage());
        }
    }

}
