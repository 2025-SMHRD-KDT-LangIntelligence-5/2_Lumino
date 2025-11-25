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
     * 새 워크스페이스 생성 (이름만으로 생성)
     * @param request Map 형태로 workspaceName, userId 받기
     * @return 생성된 Workspace
     */
    @PostMapping("/api/workspace")
    @ResponseBody
    public ResponseEntity<?> createWorkspace(@RequestBody java.util.Map<String, Object> request) {
        try {
            String workspaceName = (String) request.get("workspaceName");
            Integer userId = (Integer) request.get("userId");

            if (workspaceName == null || workspaceName.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("워크스페이스 이름은 필수입니다.");
            }

            Workspace workspace = workspaceService.createWorkspace(workspaceName, userId);
            return ResponseEntity.ok(workspace);
        } catch (Exception e) {
            log.error("워크스페이스 생성 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("워크스페이스 생성에 실패했습니다: " + e.getMessage());
        }
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

    /**
     * 워크스페이스 이름 수정
     * @param workspaceId 워크스페이스 ID
     * @param request 수정할 이름을 담은 Map (name 키로 전달)
     * @return 수정된 Workspace
     */
    @PutMapping("/api/workspace/{workspaceId}")
    @ResponseBody
    public ResponseEntity<?> updateWorkspace(@PathVariable Integer workspaceId, @RequestBody java.util.Map<String, String> request) {
        try {
            String name = request.get("name");
            if (name == null || name.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("워크스페이스 이름은 필수입니다.");
            }

            Workspace workspace = workspaceService.updateWorkspaceName(workspaceId, name);
            return ResponseEntity.ok(workspace);
        } catch (Exception e) {
            log.error("워크스페이스 수정 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("워크스페이스 수정에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 워크스페이스 삭제
     * @param workspaceId 워크스페이스 ID
     * @return 성공 여부
     */
    @DeleteMapping("/api/workspace/{workspaceId}")
    @ResponseBody
    public ResponseEntity<?> deleteWorkspace(@PathVariable Integer workspaceId) {
        try {
            workspaceService.deleteWorkspace(workspaceId);
            return ResponseEntity.ok().body("워크스페이스가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            log.error("워크스페이스 삭제 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("워크스페이스 삭제에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 모든 워크스페이스 목록 조회
     * @return 워크스페이스 리스트
     */
    @GetMapping("/api/workspaces/all")
    @ResponseBody
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        return ResponseEntity.ok(workspaces);
    }

    /**
     * 특정 워크스페이스의 상세 정보와 도구 목록 조회
     * @param workspaceId 워크스페이스 ID
     * @return WorkspaceWithToolsDTO
     */
    @GetMapping("/api/workspace/{workspaceId}")
    @ResponseBody
    public ResponseEntity<?> getWorkspaceWithTools(@PathVariable Integer workspaceId) {
        try {
            com.smhrd.boot.dto.WorkspaceWithToolsDTO workspace = workspaceService.getWorkspaceWithTools(workspaceId);
            if (workspace == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(workspace);
        } catch (Exception e) {
            log.error("워크스페이스 조회 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("워크스페이스 조회에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 워크스페이스에서 도구 삭제
     * @param workspaceId 워크스페이스 ID
     * @param toolId 삭제할 도구 ID
     * @return 성공 여부
     */
    @DeleteMapping("/api/workspace/{workspaceId}/tool/{toolId}")
    @ResponseBody
    public ResponseEntity<?> removeToolFromWorkspace(@PathVariable Integer workspaceId, @PathVariable Integer toolId) {
        try {
            workspaceService.removeToolFromWorkspace(workspaceId, toolId);
            return ResponseEntity.ok().body("도구가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            log.error("도구 삭제 중 오류 발생: ", e);
            return ResponseEntity.badRequest().body("도구 삭제에 실패했습니다: " + e.getMessage());
        }
    }

}
