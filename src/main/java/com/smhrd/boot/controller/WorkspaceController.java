package com.smhrd.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkspaceController {

    // 워크스페이스 관리 페이지 매핑
    @GetMapping("/workspace_page")
    public String workspace_page() {
        return "workspace_page"; // templates 폴더의 account-notifications.html을 찾음
    }
}
