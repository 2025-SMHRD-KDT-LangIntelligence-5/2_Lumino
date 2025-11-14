package com.smhrd.boot.controller;

import com.smhrd.boot.dto.WorkspaceWithToolsDTO;
import com.smhrd.boot.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor // lombok 사용 생성자 주입 방식
@Controller // 데이터(결과)를 반환하는 컨트롤러
public class MypageController {

    private final WorkspaceService workspaceService;

    // 마이 페이지 매핑
    @GetMapping("/my_page")
    public String my_page(Model model) {
        // 모든 워크스페이스와 tool 정보 조회
        List<WorkspaceWithToolsDTO> workspaces = workspaceService.getAllWorkspacesWithTools();
        model.addAttribute("workspaces", workspaces);

        return "my_page"; // templates 폴더의 my_page.html을 찾음
    }

}
