package com.smhrd.boot.controller;

import com.smhrd.boot.dto.WorkspaceWithToolsDTO;
import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.entity.Tool;
import com.smhrd.boot.entity.Workspace;
import com.smhrd.boot.service.CategoryService;
import com.smhrd.boot.service.MainService;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CategoryController {

    private final MainService mainService;
    private final CategoryService categoryService;
    private final WorkspaceService workspaceService;

    // 통합 카테고리 페이지 매핑 - 단일 메서드로 모든 카테고리 처리
    @GetMapping("/category_{type}")
    public String category(@PathVariable String type, Model model) {
        // 카테고리 타입별 정보 매핑
        java.util.Map<String, Object[]> categoryInfo = java.util.Map.of(
            "all", new Object[]{"전체", null},
            "text", new Object[]{"글", 1},
            "image", new Object[]{"그림", 2},
            "audio", new Object[]{"음악/음성", 3},
            "coding", new Object[]{"코딩 및 개발", 4},
            "video", new Object[]{"영상", 5},
            "search", new Object[]{"검색", 6},
            "education", new Object[]{"교육, 학습", 7}
        );

        // 카테고리 정보 가져오기
        Object[] info = categoryInfo.getOrDefault(type, new Object[]{"전체", null});
        String categoryTitle = (String) info[0];
        Integer categoryId = (Integer) info[1];

        // 도구 목록 가져오기
        List<?> tools;
        if ("all".equals(type)) {
            tools = categoryService.getRandom15Tools();
        } else {
            tools = categoryService.getToolsByCategory(categoryId);
        }

        // 디버깅: 조회된 도구 개수 로깅
        log.info("===============================================");
        log.info("Category: {} (ID: {}) - Tools count: {}", type, categoryId, tools.size());
        log.info("===============================================");

        // 워크스페이스 정보 가져오기
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();
        WorkspaceWithToolsDTO defaultWorkspace = workspaceService.getWorkspaceWithTools(1);

        // 모델에 데이터 추가
        model.addAttribute("categoryType", type);
        model.addAttribute("categoryTitle", categoryTitle);
        model.addAttribute("tools", tools);
        model.addAttribute("workspaces", workspaces);
        model.addAttribute("defaultWorkspace", defaultWorkspace);

        return "category";
    }

    // API: 특정 워크스페이스의 도구 정보 조회
    @GetMapping("/api/workspace/{id}")
    @ResponseBody
    public WorkspaceWithToolsDTO getWorkspaceTools(@PathVariable("id") Integer workspaceId) {
        return workspaceService.getWorkspaceWithTools(workspaceId);
    }

    // 디버깅: 실제 데이터베이스 도구 이름 확인
    @GetMapping("/api/debug/tools")
    @ResponseBody
    public List<Compare> getDebugTools() {
        return mainService.getRandom15Tools();
    }

}
