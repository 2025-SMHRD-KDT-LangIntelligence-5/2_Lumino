package com.smhrd.boot.controller;

import com.smhrd.boot.dto.WorkspaceWithToolsDTO;
import com.smhrd.boot.entity.Compare;
import com.smhrd.boot.service.MainService;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller
public class MainController {

    private final MainService mainService;
    private final WorkspaceService workspaceService;

    // 메인 페이지 매핑
    @GetMapping("/index")
    public String index(Model model) {
        // workspace_id = 1인 워크스페이스와 도구들을 가져옴
        WorkspaceWithToolsDTO workspace = workspaceService.getWorkspaceWithTools(1);
        model.addAttribute("workspace", workspace);

        // 모든 워크스페이스 목록 가져오기
        model.addAttribute("workspaces", workspaceService.getAllWorkspaces());

        return "index"; // templates 폴더의 index을 찾음
    }

    // 검색 기능
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Compare> tools = mainService.searchTools(keyword);
        model.addAttribute("tools", tools);
        model.addAttribute("keyword", keyword != null ? keyword : "");
        return "category_all"; // 검색 결과를 category_all 페이지에 표시
    }

    // 비동기 검색 API (JSON 반환)
    @GetMapping("/api/search")
    @ResponseBody
    public List<Compare> searchApi(@RequestParam(value = "keyword", required = false) String keyword) {
        return mainService.searchTools(keyword);
    }

}
