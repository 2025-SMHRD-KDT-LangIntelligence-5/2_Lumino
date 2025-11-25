package com.smhrd.boot.controller;

import com.smhrd.boot.entity.Community;
import com.smhrd.boot.service.CommunityService;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CommunityController {

    private final CommunityService communityService;
    private final WorkspaceService workspaceService;

    // 커뮤니티 페이지 매핑
    @GetMapping("/community_page")
    public String community_page(Model model) {
        // 모든 게시물 조회
        List<Community> posts = communityService.getAllPosts();

        // 각 게시물의 워크스페이스 정보를 Map으로 저장
        java.util.Map<Integer, com.smhrd.boot.dto.WorkspaceWithToolsDTO> workspaceMap = new java.util.HashMap<>();
        for (Community post : posts) {
            if (post.getWorkspaceId() != null) {
                com.smhrd.boot.dto.WorkspaceWithToolsDTO workspace = workspaceService.getWorkspaceWithTools(post.getWorkspaceId());
                if (workspace != null) {
                    workspaceMap.put(post.getPostIdx(), workspace);
                }
            }
        }

        model.addAttribute("posts", posts);
        model.addAttribute("workspaceMap", workspaceMap);
        return "community_page";
    }

    // 글쓰기 작성 페이지 매핑
    @GetMapping("/write_page")
    public String write_page() {
        return "write_page";
    }

    // 글쓰기 수정 페이지 매핑
    @GetMapping("/edit_page")
    public String edit_page() {
        return "edit_page";
    }

    /**
     * 커뮤니티 게시물 저장 API
     */
    @PostMapping("/api/community/post")
    @ResponseBody
    public ResponseEntity<?> savePost(@RequestBody Community post) {
        try {
            log.info("게시물 저장 요청: title={}, workspaceId={}", post.getTitle(), post.getWorkspaceId());

            // 기본값 설정
            if (post.getAuthor() == null || post.getAuthor().isEmpty()) {
                post.setAuthor("익명");
            }
            if (post.getProfileImg() == null || post.getProfileImg().isEmpty()) {
                post.setProfileImg("/assets/img/illustrations/profiles/luminosang.png");
            }
            if (post.getUserId() == null) {
                post.setUserId(1); // 기본 사용자 ID (실제로는 로그인한 사용자 ID 사용)
            }

            Community savedPost = communityService.savePost(post);
            log.info("게시물 저장 완료: postIdx={}", savedPost.getPostIdx());

            return ResponseEntity.ok(savedPost);
        } catch (Exception e) {
            log.error("게시물 저장 실패", e);
            return ResponseEntity.badRequest().body("게시물 저장에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 워크스페이스 이름과 도구 이름 목록으로 새 워크스페이스 생성
     */
    @PostMapping("/api/community/workspace/save")
    @ResponseBody
    public ResponseEntity<?> saveWorkspaceFromCommunity(@RequestBody java.util.Map<String, Object> request) {
        try {
            String workspaceName = (String) request.get("workspaceName");
            @SuppressWarnings("unchecked")
            java.util.List<String> toolNames = (java.util.List<String>) request.get("toolNames");

            log.info("워크스페이스 저장 요청: name={}, tools={}", workspaceName, toolNames);

            com.smhrd.boot.entity.Workspace savedWorkspace = workspaceService.saveWorkspaceWithTools(workspaceName, toolNames);
            log.info("워크스페이스 저장 완료: workspaceId={}", savedWorkspace.getWorkspace_id());

            return ResponseEntity.ok(savedWorkspace);
        } catch (Exception e) {
            log.error("워크스페이스 저장 실패", e);
            return ResponseEntity.badRequest().body("워크스페이스 저장에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 커뮤니티 워크스페이스를 내 워크스페이스로 복사하는 API
     */
    @PostMapping("/api/workspace/copy/{workspaceId}")
    @ResponseBody
    public ResponseEntity<?> copyWorkspace(@PathVariable Integer workspaceId) {
        try {
            log.info("워크스페이스 복사 요청: workspaceId={}", workspaceId);

            // 실제로는 로그인한 사용자 ID를 사용해야 함
            Integer userId = 1; // 임시 사용자 ID

            com.smhrd.boot.entity.Workspace copiedWorkspace = workspaceService.copyWorkspaceFromCommunity(workspaceId, userId);
            log.info("워크스페이스 복사 완료: newWorkspaceId={}", copiedWorkspace.getWorkspace_id());

            return ResponseEntity.ok(copiedWorkspace);
        } catch (Exception e) {
            log.error("워크스페이스 복사 실패", e);
            return ResponseEntity.badRequest().body("워크스페이스 복사에 실패했습니다: " + e.getMessage());
        }
    }

    /**
     * 커뮤니티 게시물 삭제 API
     */
    @DeleteMapping("/api/community/post/{postIdx}")
    @ResponseBody
    public ResponseEntity<?> deletePost(@PathVariable Integer postIdx) {
        try {
            log.info("게시물 삭제 요청: postIdx={}", postIdx);
            communityService.deletePost(postIdx);
            log.info("게시물 삭제 완료: postIdx={}", postIdx);
            return ResponseEntity.ok().body("게시물이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            log.error("게시물 삭제 실패", e);
            return ResponseEntity.badRequest().body("게시물 삭제에 실패했습니다: " + e.getMessage());
        }
    }
}
