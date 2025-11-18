package com.smhrd.boot.controller;

import com.smhrd.boot.dto.WorkspaceWithToolsDTO;
import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.entity.Tool;
import com.smhrd.boot.entity.Workspace;
import com.smhrd.boot.service.CategoryService;
import com.smhrd.boot.service.MainService;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class CategoryController {

    private final MainService mainService;
    private final CategoryService categoryService;
    private final WorkspaceService workspaceService;

    // 카테고리 페이지 매핑 - 고정된 15개 도구 표시 + 워크스페이스 목록
    @GetMapping("/category_all")
    public String category_all(Model model) {
        List<Compare> randomTools = mainService.getRandom15Tools();
        List<Workspace> workspaces = workspaceService.getAllWorkspaces();

        // 기본 워크스페이스 (workspace_id = 1) 정보 가져오기
        WorkspaceWithToolsDTO defaultWorkspace = workspaceService.getWorkspaceWithTools(1);

        model.addAttribute("tools", randomTools);
        model.addAttribute("workspaces", workspaces);
        model.addAttribute("defaultWorkspace", defaultWorkspace);
        return "category_all";
    }

    // 카테고리 페이지 매핑 - 오디오 도구 (category_id = 3)
    @GetMapping("/category_audio")
    public String category_audio(Model model) {
        List<Tool> tools = categoryService.getToolsByCategory(3);
        model.addAttribute("tools", tools);
        return "category_audio";
    }

    // 카테고리 페이지 매핑 - 코딩 도구 (category_id = 4)
    @GetMapping("/category_coding")
    public String category_coding(Model model) {
        List<Tool> tools = categoryService.getToolsByCategory(4);
        model.addAttribute("tools", tools);
        return "category_coding";
    }

    // 카테고리 페이지 매핑 - 교육 도구 (category_id = 7)
    @GetMapping("/category_education")
    public String category_education(Model model) {
        List<Tool> tools = categoryService.getToolsByCategory(7);
        model.addAttribute("tools", tools);
        return "category_education";
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_image")
    public String category_image() {
        return "category_image"; // templates 폴더의 account-notifications.html을 찾음
    }

    // 카테고리 페이지 매핑 - 검색 도구 (category_id = 6)
    @GetMapping("/category_search")
    public String category_search(Model model) {
        List<Tool> tools = categoryService.getToolsByCategory(6);
        model.addAttribute("tools", tools);
        return "category_search";
    }

    // 카테고리 페이지 매핑
    @GetMapping("/category_text")
    public String category_text() {
        return "category_text";
    }

    // 카테고리 페이지 매핑 - 비디오 도구 (category_id = 5)
    @GetMapping("/category_video")
    public String category_video(Model model) {
        List<Tool> tools = categoryService.getToolsByCategory(5);
        model.addAttribute("tools", tools);
        return "category_video";
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
